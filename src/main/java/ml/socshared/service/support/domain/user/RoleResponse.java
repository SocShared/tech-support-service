package ml.socshared.service.support.domain.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RoleResponse {

    private UUID roleId;
    private String name;

}
