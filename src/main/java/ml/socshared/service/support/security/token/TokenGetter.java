package ml.socshared.service.support.security.token;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ml.socshared.service.support.security.jwt.JwtTokenProvider;
import ml.socshared.service.support.security.model.TokenObject;
import ml.socshared.service.support.security.request.ServiceTokenRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@Aspect
@Getter
public class TokenGetter {

    private final JwtTokenProvider jwtTokenProvider;

    public TokenGetter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        init();
    }


    private TokenObject tokenMailSender;
    private TokenObject tokenAuthService;

    private void init() {
        tokenMailSender = new TokenObject();
        tokenAuthService = new TokenObject();
    }


    @Before("execution(* ml.socshared.service.support.service.EmailSender.*(..))")
    public TokenObject initTokenMailSender() {
        if (tokenMailSender != null && jwtTokenProvider.validateServiceToken(tokenMailSender.getToken())) {
            return tokenMailSender;
        }

        ServiceTokenRequest request = new ServiceTokenRequest();
        request.setFromServiceId(UUID.fromString("31a2ee92-0e6c-45b7-b6cb-810eec2f1041"));
        request.setToServiceId(UUID.fromString("68c5c6d9-fb18-4adb-800e-faac3ac745b9"));
        request.setToSecretService(UUID.fromString("a981045d-e269-4b28-b7b7-af4a885b9dc4"));

        this.tokenMailSender.setToken(jwtTokenProvider.buildServiceToken(request).getToken());

        return tokenMailSender;
    }
    @Before("execution(* ml.socshared.service.support.service.impl.AuthInfoServiceImpl.*(..))")
    public TokenObject initTokenAuth() {
        if (tokenAuthService != null && jwtTokenProvider.validateServiceToken(tokenAuthService.getToken())) {
            return tokenAuthService;
        }

        ServiceTokenRequest request = new ServiceTokenRequest();
        request.setFromServiceId(UUID.fromString("31a2ee92-0e6c-45b7-b6cb-810eec2f1041"));
        request.setToServiceId(UUID.fromString("58c2b3d5-dfad-41af-9451-d0a26fdc9019"));
        request.setToSecretService(UUID.fromString("0cb9bb2e-ee6a-48b7-b36a-23fb07f3fa28"));

        this.tokenAuthService.setToken(jwtTokenProvider.buildServiceToken(request).getToken());

        return tokenAuthService;
    }

}
