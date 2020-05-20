package ml.socshared.service.support.service;

import ml.socshared.service.support.domain.db.QuestionDB;
import ml.socshared.service.support.domain.object.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface QuestionService {

    Integer createQuestion(Question q);
    PageResponse<ShortQuestion> getQuestionList(Pageable pageable);
    FullQuestion getFullQuestion(Integer questionId, Pageable pageable);
    Integer addCommentToQuestion(Integer questionId,  Comment comm);
    void removeQuestion(Integer questionId);
    void removeComment(Integer QestionId, Integer commentId);

}
