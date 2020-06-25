package ml.socshared.service.support.service.impl;

import lombok.extern.slf4j.Slf4j;
import ml.socshared.service.support.domain.db.CommentDB;
import ml.socshared.service.support.domain.db.QuestionDB;
import ml.socshared.service.support.domain.object.*;
import ml.socshared.service.support.exception.email.SendEmailError;
import ml.socshared.service.support.exception.impl.HttpNotFoundException;
import ml.socshared.service.support.repository.CommentRepository;
import ml.socshared.service.support.repository.QuestionRepository;
import ml.socshared.service.support.service.EmailSender;
import ml.socshared.service.support.service.QuestionService;
import ml.socshared.service.support.service.sentry.SentrySender;
import ml.socshared.service.support.service.sentry.SentryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private EmailSender emailSender;
    private QuestionRepository questionRep;
    private CommentRepository commentRep;
    private SentrySender sentrySender;

    @Autowired
    public QuestionServiceImpl(QuestionRepository qrp, CommentRepository crp, EmailSender es,
                               SentrySender sentry) {
        this.commentRep = crp;
        this.questionRep = qrp;
        this.emailSender = es;
        this.sentrySender = sentry;
    }

    @Override
    public Integer createQuestion(Question q) {

        QuestionDB qdb = new QuestionDB();
        qdb.setAuthorId(q.getAuthorId());
        qdb.setTitle(q.getTitle());
        qdb = questionRep.save(qdb);

        CommentDB cdb = new CommentDB();
        cdb.setAuthorId(q.getAuthorId());
        cdb.setText(q.getText());
        cdb.setQuestion(qdb);
        commentRep.save(cdb);
        try {
            emailSender.sendToAdministrateService("Новый вопрос",
                    "Был создан новый вопрос (" + qdb.getId() +"): \n \"" +
                    q.getTitle() + "\"\n " + q.getText() + "\n");
        } catch (SendEmailError err) {
            log.error(err.getMessage());
        }

        Map<String, Object> additional = new HashMap<>();
        additional.put("title", q.getTitle());
        additional.put("text", q.getText());
        additional.put("system_user_id", q.getAuthorId());
        sentrySender.sentryMessage("create question", additional,
                Collections.singletonList(SentryTag.CREATE_QUESTION));

        return qdb.getId();
    }

    @Override
    public PageResponse<ShortQuestion> getQuestionList(Pageable pageable) {
        Pageable p = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "time"));
        Page<QuestionDB> qp = questionRep.findAll(p);
        List<ShortQuestion> res = new LinkedList<>();
        for(QuestionDB el : qp) {
            ShortQuestion q = new ShortQuestion();
            q.setId(el.getId());
            q.setAuthorId(el.getAuthorId());
            q.setTime(el.getTime());
            q.setTitle(el.getTitle());
            res.add(q);
        }
        PageResponse<ShortQuestion> page = new PageResponse<>();
        page.setData(res);
        page.setPage(pageable.getPageNumber());
        page.setSize(qp.getSize());
        page.setTotalElements(qp.getTotalElements());

        Map<String, Object> additional = new HashMap<>();
        additional.put("question_list", page);
        sentrySender.sentryMessage("get questions page", additional,
                Collections.singletonList(SentryTag.GET_QUESTIONS));

        return page;
    }

    @Override
    public FullQuestion getFullQuestion(Integer questionId, Pageable pageable) {
        Optional<QuestionDB> q = questionRep.findById(questionId);
        if(q.isEmpty()) {
            throw new HttpNotFoundException("question this id ("+ String.valueOf(questionId) + ") not found");
        }
        Pageable p = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC,"time" ));
        Page<CommentDB> pc = commentRep.findAllByQuestion_Id(questionId, p);
        List<Comment> comments = new LinkedList<>();
        for(CommentDB el : pc) {
            Comment comment = new Comment();
            comment.setId(el.getId());
            comment.setAuthorId(el.getAuthorId());
            comment.setText(el.getText());
            comment.setTime(el.getTime());
            comment.setTime(el.getTime());
            comments.add(comment);
        }
        FullQuestion res = new FullQuestion();
        res.setComments(new PageResponse<Comment>(pc.getSize(),pc.getNumber(),pc.getTotalElements(), pc.getTotalPages(),comments));
        res.setAuthorId(q.get().getAuthorId());
        res.setQuestionId(q.get().getId());
        res.setTitle(q.get().getTitle());

        Map<String, Object> additional = new HashMap<>();
        additional.put("question_id", questionId);
        additional.put("num_answers", pc.getSize() - 1);
        sentrySender.sentryMessage("get full question page", additional,
                Collections.singletonList(SentryTag.GET_FULL_QUESTION));

        return res;
    }

    @Override
    public Integer addCommentToQuestion(Integer questionId, Comment comm) {
        Optional<QuestionDB> q = questionRep.findById(questionId);

        if(q.isEmpty()) {
            String msg = "Not found question with id: " + String.valueOf(questionId);
            log.info(msg);
            throw new HttpNotFoundException(msg);
        }
        QuestionDB qd = q.get();

        CommentDB comment = new CommentDB();
        comment.setText(comm.getText());
        comment.setQuestion(q.get());
        comment.setAuthorId(comm.getAuthorId());
        comment = commentRep.save(comment);

        if(!comm.getAuthorId().equals(qd.getAuthorId())) {
            try {
                emailSender.sendToUser("Ответ на вопрос: " + String.valueOf(qd.getId()),
                        "Добрый день, на ваш вопрос: " + qd.getTitle() + "\n вы получили ответ.", qd.getAuthorId());
            } catch (SendEmailError err) {
                log.error(err.getMessage());
            }

        }

        Map<String, Object> additional = new HashMap<>();
        additional.put("question_id", questionId);
        additional.put("system_user_id", comm.getAuthorId());
        sentrySender.sentryMessage("create comment for question", additional,
                Collections.singletonList(SentryTag.ADD_COMMENT));

        return comment.getId();
    }

    @Override
    @Transactional
    public void removeQuestion(Integer questionId) {
        Optional<QuestionDB> q = questionRep.findById(questionId);
        if(q.isEmpty()) {
            String msg = "Not found question with id: " + String.valueOf(questionId);
            log.info(msg);
            throw new HttpNotFoundException(msg);
        }
        commentRep.deleteCommentDBByQuestion(q.get());

        questionRep.delete(q.get());


        Map<String, Object> additional = new HashMap<>();
        additional.put("question_id", questionId);
        sentrySender.sentryMessage("remove question", additional,
                Collections.singletonList(SentryTag.REMOVE_QUESTION));
    }

    @Override
    public void removeComment(Integer questionId, Integer commentId) {
        Optional<CommentDB> c = commentRep.findById(questionId, commentId);
        if(c.isEmpty()) {
            String msg = "Not found comment with id: (QuestionId:" + String.valueOf(questionId)
                    + "; CommentId: " + String.valueOf(commentId) + ")";
            log.info(msg);
            throw new HttpNotFoundException(msg);
        }

        Map<String, Object> additional = new HashMap<>();
        additional.put("question_id", questionId);
        additional.put("comment_id", commentId);
        sentrySender.sentryMessage("remove comment", additional,
                Collections.singletonList(SentryTag.REMOVE_COMMENT));

        commentRep.delete(c.get());
    }
}
