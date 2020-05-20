package ml.socshared.service.support.client.impl;

import lombok.extern.slf4j.Slf4j;
import ml.socshared.service.support.client.EmailSenderClient;
import ml.socshared.service.support.domain.client.SendMessageRequest;
import ml.socshared.service.support.domain.client.SuccessResponse;

@Slf4j
public class EmailSenderClientMock implements EmailSenderClient {
    @Override
    public SuccessResponse sendMessage(SendMessageRequest message) {
        log.info("sendMessage: " +  message.toString());
        SuccessResponse res = new SuccessResponse();
        res.setSuccess(true);
        return res;
    }
}
