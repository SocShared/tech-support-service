package ml.socshared.service.support.client;

import feign.RequestLine;
import ml.socshared.service.support.config.Constants;
import ml.socshared.service.support.domain.client.SendMessageRequest;
import ml.socshared.service.support.domain.client.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "EmailSenderAPIClient", url = Constants.URL_EMAIL_SENDER_SERVICE)
public interface EmailSenderClient {
    @RequestLine("POST /api/v1/message")
    SuccessResponse sendMessage(SendMessageRequest message);
}
