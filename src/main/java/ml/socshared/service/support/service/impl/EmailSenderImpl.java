package ml.socshared.service.support.service.impl;

import ml.socshared.service.support.client.EmailSenderClient;
import ml.socshared.service.support.domain.client.SendMessageRequest;
import ml.socshared.service.support.domain.client.SuccessResponse;
import ml.socshared.service.support.exception.email.SendEmailError;
import ml.socshared.service.support.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.*;

@Service
public class EmailSenderImpl implements EmailSender {

    EmailSenderClient client;

    @Autowired
    EmailSenderImpl(@Qualifier("getEmailAPIClient") EmailSenderClient client) {
        this.client = client;
    }

    @Override
    public void sendToAdministrateService(String subject, String message) throws SendEmailError {
        List<String> emails = Arrays.asList("some1@example.com", "some2@example.com");
        SuccessResponse res = client.sendMessage(new SendMessageRequest(subject, message, emails));
        if(!res.getSuccess()) {
            throwErrorSendingMessage(emails);
        }
    }

    @Override
    public void sendToUser(String subject, String message, UUID userId) throws SendEmailError {
        List<String> email = Collections.singletonList( "client@example.com"); //TODO получяем почту по айди!
        SuccessResponse res = client.sendMessage(new SendMessageRequest(subject, message, email));
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
