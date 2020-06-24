package ml.socshared.service.support.service;


import ml.socshared.service.support.domain.user.UserResponse;

import java.util.UUID;

public interface AuthInfoService {
    UserResponse getClientInfoById(UUID clientId);
}
