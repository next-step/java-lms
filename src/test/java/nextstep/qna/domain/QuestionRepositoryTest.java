package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionId;
import nextstep.qna.domain.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuestionRepositoryTest {

    @Autowired QuestionRepository questionRepository;

    @DisplayName("id 로 조회한다")
    @Test
    public void findByQuestionId() {
        //findById << 통합필요
        //given
        //when
        Question question = questionRepository.findByQuestionId(new QuestionId(1L)).get();
        //then
        System.out.println(question.toString());
    }

    @DisplayName("findAll")
    @Test
    public void findAll() {
        //given
        //when
        questionRepository.findAll();
        //then
    }
    @DisplayName("save")
    @Test
    public void save() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        //when
        Question save = questionRepository.save(question);
        //then
        System.out.println(save.toString());
    }
}