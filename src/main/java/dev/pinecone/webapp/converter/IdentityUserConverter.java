package dev.pinecone.webapp.converter;

import dev.pinecone.webapp.model.IdentityUser;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class IdentityUserConverter {

    public IdentityUser getUser(Claims claims) {
        return IdentityUser.builder()
                .id(getStringValue(claims, "id"))
                .build();
    }

    private String getStringValue(Claims claims, String key) {
        Object foundValue = claims.getOrDefault(key, StringUtils.EMPTY);
        return foundValue == null ? null : foundValue.toString();
    }

}