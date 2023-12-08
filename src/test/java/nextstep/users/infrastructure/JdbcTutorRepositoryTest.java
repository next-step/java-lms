package nextstep.users.infrastructure;

import nextstep.tutor.infrastructure.JdbcTutorRepository;
import nextstep.tutor.domain.NsTutor;
import nextstep.tutor.domain.TutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcTutorRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TutorRepository tutorRepository;

    @BeforeEach
    void setUp() {
        tutorRepository = new JdbcTutorRepository(jdbcTemplate);
    }

    @Test
    void findByTutorId() {
        Optional<NsTutor> tutor = tutorRepository.findByTutorId("testrace");
        assertThat(tutor.isEmpty()).isFalse();
        LOGGER.debug("Tutor: {}", tutor.get());
    }
}
