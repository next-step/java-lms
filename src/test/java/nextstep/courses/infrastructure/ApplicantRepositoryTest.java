package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.repository.StudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class ApplicantRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicantRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void save() {
        Session session = SessionTest.SESSION1;
        Applicant applicant = new Applicant(NsUserTest.JAVAJIGI, session, null);
        long id = studentRepository.save(applicant);
        assertThat(id).isEqualTo(applicant.getId());

        Applicant saved = studentRepository.findById(1L);
        assertThat(saved.getSession()).isEqualTo(session);
        assertThat(saved.getNsUser()).isEqualTo(NsUserTest.JAVAJIGI);
        LOGGER.debug("Student: {}", saved);
    }

}
