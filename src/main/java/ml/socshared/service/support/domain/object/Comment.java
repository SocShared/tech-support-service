package ml.socshared.service.support.domain.object;

import lombok.Data;

import java.util.UUID;

@Data
public class Comment {
    UUID authorId;
    String text;
}
