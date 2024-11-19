package dev.pinecone.webapp.utils.constants;

import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class GeneralConstants {
    public static final String DEFAULT_LANGUAGE = "TR";

    public static final Set<String> WHITE_LIST_PATH_SET = Set.of(
            "/mail/verify",
            "/phone/verify",
            "/onboard/trendyol",
            "/send-phone-verification-trendyol",
            "/register",
            "/regions",
            "/countries",
            "/payments/3ds/craftgate-callback",
            "/otp/generate",
            "/otp/login",
            "/default-warranty-document-histories/document",
            "/panel/newsletter",
            "/"
    );

    public static final Set<String> DEFAULT_WHITE_LIST_SET = Set.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/v2/**",
            "/actuator/**",
            "/favicon.ico",
            "/csrf"
    );
}