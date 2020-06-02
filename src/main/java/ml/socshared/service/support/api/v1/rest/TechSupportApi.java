package ml.socshared.service.support.api.v1.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ml.socshared.service.support.domain.object.*;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.HashMap;

@Api(value = "Controller for tech-support api")
public interface TechSupportApi {
    @ApiOperation(value = "create question", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Question created")
    })
    Integer addQuestion(Question question);

    @ApiOperation(value = "Returns a short list of questions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="successful")
    })
    PageResponse<ShortQuestion> getQuestionsList(Pageable pageable);

    @ApiOperation(value = "get question and list comments", response = FullQuestion.class,
    httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return question and list comments"),
            @ApiResponse(code = 404, message = "question not found")
    })
    FullQuestion getFullQuestion(Integer questionId, Pageable pageable);

    @ApiOperation(value = "create comment of question", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "comment created"),
            @ApiResponse(code = 404, message = "question not found")
    })
    Integer addCommentToQuestion(Integer questionId,Comment comment);

    @ApiOperation(value = "removing question and all comments of question", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "question deleted"),
            @ApiResponse(code = 404, message = "question not found")
    })
    void removeQuestion(Integer questionId);

    @ApiOperation(value = "removing comment of question", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment deleted"),
            @ApiResponse(code = 404, message = "Question or comment not found")
    })
    void removeComment(Integer questionId, Integer commentId);
}
