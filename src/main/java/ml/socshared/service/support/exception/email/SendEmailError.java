package ml.socshared.service.support.exception.email;

public class SendEmailError extends Exception {
    public SendEmailError(String msg) {
        super(msg);
    }
}
