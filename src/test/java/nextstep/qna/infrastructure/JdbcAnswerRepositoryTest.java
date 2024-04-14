package nextstep.qna.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.AnswerRepository;
import nextstep.users.infrastructure.UserRepositoryTest;

@JdbcTest
class JdbcAnswerRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        answerRepository = new JdbcAnswerRepository(jdbcTemplate);
    }

    @Test
    void findByQuestion() {
        final List<Answer> answers = answerRepository.findByQuestion(1L);
        assertThat(answers).hasSize(2);
        LOGGER.debug("answers: {}", answers);
    }
}
