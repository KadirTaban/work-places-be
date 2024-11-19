package dev.pinecone.webapp.utils.constants;

import java.util.Set;

public final class HeaderConstants {
    public static final String X_TRACE_ID = "x-trace-id";
    public static final String ACCEPT_LANGUAGE = "accept-language";
    public static final String X_CONSUMER_ID = "x-consumer-id";
    public static final String AUTHORIZATION = "Authorization";
    public static final Set<String> INTERNAL_HEADERS = Set.of(
            X_TRACE_ID,
            ACCEPT_LANGUAGE,
            X_CONSUMER_ID
    );

    private HeaderConstants() {
    }
}
