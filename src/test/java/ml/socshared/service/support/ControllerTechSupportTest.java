package ml.socshared.service.support;

import ml.socshared.service.support.domain.db.CommentDB;
import ml.socshared.service.support.domain.db.QuestionDB;
import ml.socshared.service.support.integration.AbstractIT;
import ml.socshared.service.support.repository.CommentRepository;
import ml.socshared.service.support.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTechSupportTest {

    UUID author1 = UUID.randomUUID();
    UUID author2 = UUID.randomUUID();
    UUID author3 = UUID.randomUUID();


    @Autowired
    MockMvc mockMvc;

    @Autowired
    QuestionRepository questionRep;
    @Autowired
    CommentRepository commentRep;


    @BeforeEach
    public void loadData() {
        QuestionDB q1 = new QuestionDB();
        CommentDB c1 = new CommentDB();
        q1.setTitle("q1");
        q1.setAuthorId(author1);
        q1 = questionRep.save(q1);
        c1.setAuthorId(author1);
        c1.setQuestion(q1);
        c1.setText("text1");
        commentRep.save(c1);
        CommentDB c2 = new CommentDB();
        c2.setText("answer1");
        c2.setQuestion(q1);
        c2.setAuthorId(author2);
        commentRep.save(c2);
        QuestionDB q2 = new QuestionDB();
        CommentDB c3 = new CommentDB();
        q1.setTitle("q1");
        q1.setAuthorId(author1);
        q2 = questionRep.save(q2);
        c3.setAuthorId(author1);
        c3.setQuestion(q2);
        c3.setText("text2");
        commentRep.save(c3);

    }

    @Test
    public void loadContextTest() throws Exception {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    public  void pageOfListQuestionsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questions"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
