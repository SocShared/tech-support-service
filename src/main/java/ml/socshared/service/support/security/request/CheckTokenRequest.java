package ml.socshared.service.support.security.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckTokenRequest {

    private String token;

}
