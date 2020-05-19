package ml.socshared.service.support.domain.object;

import lombok.Data;

import java.util.UUID;

@Data
public class Question {
    UUID authorId;
    String title;
    String text;
}
