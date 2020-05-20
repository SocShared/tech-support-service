package ml.socshared.service.support.service;

import ml.socshared.service.support.exception.email.SendEmailError;

import java.util.UUID;

public interface EmailSender {
    void sendToAdministrateService(String subject, String message) throws SendEmailError;
    void sendToUser(String subject, String message, UUID userId) throws SendEmailError;
}
