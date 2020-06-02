package ml.socshared.service.support.repository;

import ml.socshared.service.support.domain.db.CommentDB;
import ml.socshared.service.support.domain.db.QuestionDB;
import ml.socshared.service.support.domain.object.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<CommentDB,Integer> {

    @Query("SELECT " +
            " new java.lang.Long(COUNT(c))" +
            "FROM CommentDB c GROUP BY c.question HAVING c.question.id = :questionId")
    Long getCountComments(Integer questionId);

    Page<CommentDB> findAllByQuestion_Id(Integer questionId, Pageable pageable);

    @Query("SELECT c FROM CommentDB c WHERE c.question.id = :questionId AND c.id = :commentId")
    Optional<CommentDB> findById(Integer questionId, Integer commentId);

    void deleteCommentDBByQuestion(QuestionDB q);
}
