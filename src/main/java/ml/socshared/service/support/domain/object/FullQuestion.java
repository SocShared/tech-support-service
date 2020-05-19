package ml.socshared.service.support.domain.object;

import lombok.Data;

import java.util.List;

@Data
public class FullQuestion extends Question {
    Page<Comment> comments;
}
