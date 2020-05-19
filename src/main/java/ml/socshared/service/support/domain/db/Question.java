package ml.socshared.service.support.domain.db;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    Integer id;
    @Column(name="author_id")
    UUID authorId;
    @Column(name="text")
    String text;
}
