package ml.socshared.service.support.client;

import ml.socshared.service.support.domain.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name="AuthApiClient", url = "${feign.url.auth:}")
public interface AuthInfoClient {

    @GetMapping(value = "api/v1/private/users/{systemUserId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserResponse getUserById(@PathVariable UUID systemUserId,
                             @RequestHeader("Authorization") String token);
}
