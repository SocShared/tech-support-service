package ml.socshared.service.support.domain.db;

import lombok.Data;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="comment")
public class CommentDB {
    @Id
    @GeneratedValue
    Integer id;
    @Column(name = "author_id")
    UUID authorId;
    @Column(name="text")
    String text;
    @ManyToOne
    @JoinColumn(name = "question_id")
    QuestionDB question;

    ZonedDateTime time =  ZonedDateTime.now(ZoneOffset.UTC);
}
