package ml.socshared.service.support.domain.db;

import lombok.Data;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "question")
public class QuestionDB {
    @Id
    @GeneratedValue
    Integer id;
    @Column(name="author_id")
    UUID authorId;
    @Column(name="title")
    String title;
    @Column(name="time")
    ZonedDateTime time = ZonedDateTime.now(ZoneOffset.UTC);
}
