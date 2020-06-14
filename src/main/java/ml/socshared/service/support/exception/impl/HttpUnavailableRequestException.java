package ml.socshared.service.support.exception.impl;

import ml.socshared.service.support.exception.AbstractRestHandleableException;
import ml.socshared.service.support.exception.SocsharedErrors;
import org.springframework.http.HttpStatus;

public class HttpUnavailableRequestException extends AbstractRestHandleableException {
    public HttpUnavailableRequestException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpUnavailableRequestException(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
