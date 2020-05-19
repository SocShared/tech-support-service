package ml.socshared.service.support.domain.db;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue
    Integer id;
    @Column(name = "author_id")
    UUID authorId;
    @Column(name="text")
    String text;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name="question")
    Question question;
}
