package ml.socshared.service.support.exception.impl;

import ml.socshared.service.support.exception.AbstractRestHandleableException;
import ml.socshared.service.support.exception.SocsharedErrors;
import org.springframework.http.HttpStatus;

public class HttpBadRequestException extends AbstractRestHandleableException {
    public HttpBadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public HttpBadRequestException(SocsharedErrors errorCode, HttpStatus httpStatus) {
        super(httpStatus);
    }

    public HttpBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}