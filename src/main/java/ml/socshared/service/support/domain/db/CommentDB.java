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
    @Column(name="text", length = 1000)
    String text;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    QuestionDB question;

    ZonedDateTime time =  ZonedDateTime.now(ZoneOffset.UTC);
}
