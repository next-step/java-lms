package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import nextstep.qna.infrastructure.JdbcAnswerRepository;
import nextstep.qna.infrastructure.JdbcQuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
public class AnswerRepositoryTest {

    @Autowired private JdbcTemplate jdbcTemplate;
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        TestFixture.fixtureInit();
        answerRepository = new JdbcAnswerRepository(jdbcTemplate);
        questionRepository = new JdbcQuestionRepository(jdbcTemplate);
    }

    @AfterEach
    void wrapUp() {
        //courseRepository.deleteAll();
    }

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
        List<Answer> byQuestion = answerRepository.findAllByQuestion(1L);
        //then
        fail();
    }
}