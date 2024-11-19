package dev.pinecone.webapp.security;

import dev.pinecone.webapp.converter.IdentityUserConverter;
import dev.pinecone.webapp.model.ErrorDetail;
import dev.pinecone.webapp.model.UserAuthentication;
import dev.pinecone.webapp.model.error.ErrorEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static dev.pinecone.webapp.utils.constants.SecurityConstant.BEARER;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final IdentityUserConverter identityUserConverter;

    private final static String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public String extractFromUserId(String token) {
        return extractClaim(token, Claims::getId);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String, Object> tokenData, UserDetails userDetails) {
        return BEARER.formatted(
                Jwts.builder()
                        .addClaims(tokenData)
                        .setSubject(userDetails.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                        .compressWith(CompressionCodecs.GZIP)
                        .signWith(SignatureAlgorithm.HS256, getSignInKey())
                        .compact()
        );
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Claims getClaims(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt)
                .getBody();
    }

    public String getTokenFromHeader(HttpServletRequest httpServletRequest) {
        String authenticationHeader = httpServletRequest.getHeader("Authorization");
        boolean startWithBearer = StringUtils.startsWith(authenticationHeader, "Bearer");
        String[] headerParams = StringUtils.split(authenticationHeader, StringUtils.SPACE);
        boolean headerParamSizeIsTwo = ArrayUtils.getLength(headerParams) == 2;
        boolean isAuthenticationHeaderValid = authenticationHeader != null && startWithBearer && headerParamSizeIsTwo;
        if (!isAuthenticationHeaderValid) {
            ErrorDetail errorDetail = new ErrorDetail(ErrorEnum.UNAUTHORIZED.getCode(), "Authentication header not valid");
            throw new RuntimeException("hayat zor kirvem.");
        }
        return authenticationHeader.split(StringUtils.SPACE)[1];
    }


    public Authentication getUserAuthentication(HttpServletRequest httpServletRequest) {
        String jwt = getTokenFromHeader(httpServletRequest);
        Claims claims = getClaims(jwt);
        return new UserAuthentication(identityUserConverter.getUser(claims));
    }
}
