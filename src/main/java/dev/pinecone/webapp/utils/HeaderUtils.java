package com.egaranti.bffservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.util.Locale;

import static com.egaranti.bffservice.model.constant.GeneralConstants.DEFAULT_LANGUAGE;
import static egaranti.common.central.lib.model.constant.HeaderConstants.ACCEPT_LANGUAGE;
import static egaranti.common.central.lib.model.constant.HeaderConstants.X_MERCHANT_ID;
import static egaranti.common.central.lib.model.constant.HeaderConstants.X_PARTNER_ID;
import static egaranti.common.central.lib.model.constant.HeaderConstants.X_TRACE_ID;
import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HeaderUtils {

    public static String getTraceId() {
        return MDC.get(X_TRACE_ID);
    }

    public static Integer getMerchantId() {
        return Integer.valueOf(MDC.get(X_MERCHANT_ID));
    }

    public static String getAcceptLanguage() {
        return MDC.get(ACCEPT_LANGUAGE);
    }

    public static Locale getLocale() {
        String languageHeader = getAcceptLanguage();
        return new Locale(isBlank(languageHeader) ? DEFAULT_LANGUAGE : languageHeader);
    }

    public static Integer getPartnerId() {
        String partnerId = MDC.get(X_PARTNER_ID);
        return Integer.valueOf(partnerId);
    }

}
