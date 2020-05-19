package ml.socshared.service.support.repository;

import ml.socshared.service.support.domain.db.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Integer> {
}
