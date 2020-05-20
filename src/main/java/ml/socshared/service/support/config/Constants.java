package ml.socshared.service.support.config;

import org.springframework.beans.factory.annotation.Value;

public final class Constants {

    private Constants() {}

    public static final String DEV_PROFILE = "dev";
    public static final String TEST_PROFILE = "test";
    public static final String LOCAL_PROFILE = "local";
    public static final String PROD_PROFILE = "prod";

    @Value("service.tech-support.service-url.email-sender")
    public static final String URL_EMAIL_SENDER_SERVICE = "localhost";

}
