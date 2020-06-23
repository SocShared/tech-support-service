package ml.socshared.service.support.service.impl;

import lombok.RequiredArgsConstructor;
import ml.socshared.service.support.client.AuthInfoClient;
import ml.socshared.service.support.domain.user.UserResponse;
import ml.socshared.service.support.security.model.TokenObject;
import ml.socshared.service.support.service.AuthInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthInfoServiceImpl implements AuthInfoService {

    private final AuthInfoClient authInfoClient;

    @Value("#{tokenGetter.tokenAuthService}")
    TokenObject authToken;

    @Override
    public UserResponse getClientInfoById(UUID clientId) {
        return authInfoClient.getUserById(clientId, "Bearer " + authToken.getToken());
    }
}
