package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.fixture.SessionFixtures.SESSION;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcStudentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcStudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    public void session_에_유저가_수강신청_할_수_있다() throws Exception {
        Student student = new Student(SESSION.getId(), 1L);
        int count = studentRepository.save(student);
        assertThat(count).isEqualTo(1);

        List<NsUser> allUsers = studentRepository.findAllBySessionId(SESSION.getId());
        assertThat(allUsers).hasSize(1);
        assertThat(allUsers.get(0).getId()).isEqualTo(1L);
    }
}