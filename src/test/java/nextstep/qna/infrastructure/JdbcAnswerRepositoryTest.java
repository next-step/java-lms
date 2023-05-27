package nextstep.qna.infrastructure;

import nextstep.fixture.TestFixture;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JdbcAnswerRepositoryTest {

    @Autowired private AnswerRepository answerRepository;
    @Autowired private QuestionRepository questionRepository;

    @DisplayName("저장한다")
    @Test
    public void 저장한다() {
        //given
        Answer answer = TestFixture.BADAJIGI_ANSWER;
        //when
        Answer save = answerRepository.save(answer);
        //then
        assertThat(save).as("id 할당받음 검증").isNotNull();
    }

    @DisplayName("Question 과 연관된 모든 Ansewer을 조회한다")
    @Test
    public void findByQuestion() {
        //given
        //when
        List<Answer> byQuestion = answerRepository.findByQuestion(1L);
        //then
        fail();
    }
}