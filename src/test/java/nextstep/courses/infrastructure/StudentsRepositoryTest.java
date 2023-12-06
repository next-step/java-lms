package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Students;
import nextstep.courses.domain.session.StudentsRepository;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentsRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcUserRepository(jdbcTemplate);
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate, userRepository);
    }

    @Test
    void crud() {
        int count = studentsRepository.save(1L, Students.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        assertThat(count).isEqualTo(2);
        Students saveStudents = studentsRepository.findBySessionId(1L);
        assertThat(saveStudents.size()).isEqualTo(2);
        LOGGER.debug("saveStudents: {}", saveStudents);
    }
}
