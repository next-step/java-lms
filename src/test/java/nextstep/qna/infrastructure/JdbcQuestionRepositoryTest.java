package nextstep.qna.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.infrastructure.UserRepositoryTest;

@JdbcTest
class JdbcQuestionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        questionRepository = new JdbcQuestionRepository(jdbcTemplate);
    }

    @Test
    void findById() {
        final Optional<Question> question = questionRepository.findById(1L);
        assertThat(question.isEmpty()).isFalse();
        LOGGER.debug("NsUser: {}", question.get());
    }
}
