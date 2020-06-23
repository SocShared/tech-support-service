package ml.socshared.service.support.client;

import feign.RequestLine;
import ml.socshared.service.support.config.Constants;
import ml.socshared.service.support.domain.client.SendMessageRequest;
import ml.socshared.service.support.domain.client.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "EmailSenderAPIClient", url = "${feign.url.email-sender}")
public interface EmailSenderClient {
    @PostMapping(value = "/api/v1/message",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessResponse sendMessage(@RequestBody SendMessageRequest message);
}
