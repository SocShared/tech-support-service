package ml.socshared.service.support.controller.v1;

import lombok.extern.slf4j.Slf4j;
import ml.socshared.service.support.domain.object.*;
import ml.socshared.service.support.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ml.socshared.service.support.api.v1.rest.TechSupportApi;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
@Validated
public class TechSupportController implements TechSupportApi {

    private QuestionService service;


    TechSupportController(QuestionService qs) {
        service = qs;
    }

    @Override
    @PostMapping("/private/questions")
    public Integer addQuestion(@RequestBody Question question) {
        log.info("request of create new question");
        return service.createQuestion(question);

    }

    @Override
    @GetMapping("/private/questions")
    public PageResponse<ShortQuestion> getQuestionsList(Pageable pageable) {
        log.info("Request on get questions list {}", pageable);
        return service.getQuestionList(pageable);
    }

    @Override
    @GetMapping("/private/questions/{questionId}")
    public FullQuestion getFullQuestion(@PathVariable Integer questionId, Pageable pageable) {
        log.info("Request on get page of full question id {}, on page {}", questionId, pageable);
        return service.getFullQuestion(questionId, pageable);
    }


    @Override
    @PostMapping("/private/questions/{questionId}/comments")
    public Integer addCommentToQuestion(@PathVariable Integer questionId,
                                        @RequestBody Comment comment) {
        log.info("Request of adding comment to Question");
        return service.addCommentToQuestion(questionId, comment);
    }

    @Override
    @DeleteMapping("/private/questions/{questionId}")
    public void removeQuestion(@PathVariable Integer questionId) {
        log.info("request of removing question by id: {}", questionId);
        service.removeQuestion(questionId);
    }

    @Override
    @DeleteMapping("/private/questions/{questionId}/comments/{commentId}")
    public void removeComment(@PathVariable Integer questionId,@PathVariable Integer commentId) {
        log.info("Request of removing comment (questionId {}, commentId: {})", questionId, commentId);
        service.removeComment(questionId, commentId);
    }
}
