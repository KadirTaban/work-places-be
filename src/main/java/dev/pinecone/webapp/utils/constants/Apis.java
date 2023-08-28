package dev.pinecone.webapp.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Apis {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        public static final String BASE_URL = "/api/v1/users";
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Country {
        public static final String BASE_URL = "/api/v1/countries";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Place {
        public static final String BASE_URL = "/api/v1/places";
        public static final String CREATE = "/create";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class City {
        public static final String BASE_URL = "/api/v1/cities";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class District {
        public static final String BASE_URL = "/api/v1/districts";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Rate {
        public static final String BASE_URL = "/api/v1/rate";
    }
}
