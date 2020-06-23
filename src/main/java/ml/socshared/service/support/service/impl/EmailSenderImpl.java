package ml.socshared.service.support.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.service.support.client.EmailSenderClient;
import ml.socshared.service.support.domain.client.SendMessageRequest;
import ml.socshared.service.support.domain.client.SuccessResponse;
import ml.socshared.service.support.domain.user.UserResponse;
import ml.socshared.service.support.exception.email.SendEmailError;
import ml.socshared.service.support.security.jwt.JwtTokenProvider;
import ml.socshared.service.support.security.model.TokenObject;
import ml.socshared.service.support.service.AuthInfoService;
import ml.socshared.service.support.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.MessageDigest;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final EmailSenderClient client;
    private final AuthInfoService authInfoService;

    @Value("#{tokenGetter.getTokenMailSender()}")
    private TokenObject tokenMailSender;

    @Override
    public void sendToAdministrateService(String subject, String message) throws SendEmailError {
        List<String> emails = Arrays.asList("some1@example.com", "some2@example.com");
        //SuccessResponse res = client.sendMessage(new SendMessageRequest(subject, message, emails));
//        if(!res.getSuccess()) {
//            throwErrorSendingMessage(emails);
//        }
    }

    @Override
    public void sendToUser(String subject, String message, UUID userId) throws SendEmailError {
        UserResponse user = authInfoService.getClientInfoById(userId);
        List<String> email = Collections.singletonList(user.getEmail());
        SuccessResponse res = client.sendMessage(new SendMessageRequest(subject, message, email), "Bearer " + tokenMailSender.getToken());
        if(!res.getSuccess()) {
            throwErrorSendingMessage(email);
        }
    }

    private void throwErrorSendingMessage(List<String> to) throws SendEmailError {
        StringBuilder msg = new StringBuilder("error send message to ");
        for(String el : to) {
           msg.append(el + "; ");
        }
        throw new SendEmailError(msg.toString());
    }


}
