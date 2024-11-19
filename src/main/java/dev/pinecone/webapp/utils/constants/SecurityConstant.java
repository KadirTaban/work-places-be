package dev.pinecone.webapp.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstant {

    public static final String BEARER = "Bearer %s";
    public static final int TOKEN_EXPIRATION_WEEKS_COUNT = 4;

}