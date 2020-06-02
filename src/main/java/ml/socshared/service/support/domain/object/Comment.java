package ml.socshared.service.support.domain.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class Comment {
    Integer id;
    @JsonProperty(required = true)
    UUID authorId;
    @JsonProperty(required = true)
    String text;
    ZonedDateTime time;
}
