package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.repository.ApplicantRepository;
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

    private ApplicantRepository applicantRepository;

    @BeforeEach
    void setUp() {
        applicantRepository = new JdbcApplicantRepository(jdbcTemplate);
    }

    @Test
    void save() {
        Session session = SessionTest.SESSION1;
        Applicant applicant = new Applicant(NsUserTest.JAVAJIGI, session.getId(), null);
        long id = applicantRepository.save(applicant);
        assertThat(id).isEqualTo(applicant.getId());

        Applicant saved = applicantRepository.findById(id);
        assertThat(saved.getSessionId()).isEqualTo(session.getId());
        assertThat(saved.getNsUser()).isEqualTo(NsUserTest.JAVAJIGI);
        LOGGER.debug("Student: {}", saved);
    }

}
