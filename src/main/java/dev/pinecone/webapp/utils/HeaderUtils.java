package dev.pinecone.webapp.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.util.Locale;

import static dev.pinecone.webapp.utils.constants.GeneralConstants.DEFAULT_LANGUAGE;
import static dev.pinecone.webapp.utils.constants.HeaderConstants.ACCEPT_LANGUAGE;
import static dev.pinecone.webapp.utils.constants.HeaderConstants.X_CONSUMER_ID;
import static dev.pinecone.webapp.utils.constants.HeaderConstants.X_TRACE_ID;
import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HeaderUtils {

    public static String getTraceId() {
        return MDC.get(X_TRACE_ID);
    }

    public static Integer getConsumerId() {
        return Integer.valueOf(MDC.get(X_CONSUMER_ID));
    }

    public static String getAcceptLanguage() {
        return MDC.get(ACCEPT_LANGUAGE);
    }

    public static Locale getLocale() {
        String languageHeader = getAcceptLanguage();
        return new Locale(isBlank(languageHeader) ? DEFAULT_LANGUAGE : languageHeader);
    }

}
