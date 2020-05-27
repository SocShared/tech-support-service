package ml.socshared.service.support.config;

import ml.socshared.service.support.client.EmailSenderClient;
import ml.socshared.service.support.client.impl.EmailSenderClientMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({Constants.LOCAL_PROFILE, Constants.TEST_PROFILE, Constants.DEV_PROFILE})
public class TestFeignConfig {
    @Bean
    EmailSenderClient getEmailAPIClient() {
        return new EmailSenderClientMock();
    }
}
