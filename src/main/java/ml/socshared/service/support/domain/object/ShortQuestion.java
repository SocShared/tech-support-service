package ml.socshared.service.support.domain.object;

import lombok.Data;

import java.util.UUID;

@Data
public class ShortQuestion {
    String title;
    UUID authorId;
    Boolean haveResponse;
}
