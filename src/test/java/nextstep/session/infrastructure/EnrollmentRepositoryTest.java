package nextstep.session.infrastructure;

import nextstep.session.domain.AdmissionRepository;
import nextstep.session.domain.Enrollment;
import nextstep.session.domain.EnrollmentRepository;
import nextstep.session.domain.FreeSession;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionImageRepository;
import nextstep.session.domain.SessionRecruitStatus;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.fixture.SessionImageFixture;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class EnrollmentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionImageRepository sessionImageRepository;
    private SessionRepository sessionRepository;
    private EnrollmentRepository enrollmentRepository;
    private AdmissionRepository admissionRepository;
    private UserRepository userRepository;

    NsUser JAVAJIGI;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        admissionRepository = new JdbcAdmissionRepository(jdbcTemplate);

        sessionRepository = new JdbcSessionRepository(jdbcTemplate, sessionImageRepository, enrollmentRepository, admissionRepository);
        userRepository = new JdbcUserRepository(jdbcTemplate);

        JAVAJIGI = userRepository.findByUserId("javajigi").get();
    }

    @Test
    @DisplayName("강의등록 / 무료강의 / 성공")
    void enrollFreeSession() {
        // given
        Session freeSession = new FreeSession(1L, LocalDate.now(), LocalDate.now().plusDays(1), SessionImageFixture.createSessionImage());
        freeSession.changeRecruit(SessionRecruitStatus.OPEN);
        Session savedSession = sessionRepository.findById(sessionRepository.save(freeSession));

        // when
        Enrollment enrollment = savedSession.enroll(JAVAJIGI);
        enrollmentRepository.save(enrollment);

        // then
        assertThat(sessionRepository.findById(savedSession.getId()).enrolledNumber())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("삭제 / Enrollment / 성공")
    void deleteEnrollment() {
        // given
        Session freeSession = new FreeSession(1L, LocalDate.now(), LocalDate.now().plusDays(1), SessionImageFixture.createSessionImage());
        freeSession.changeRecruit(SessionRecruitStatus.OPEN);
        Session savedSession = sessionRepository.findById(sessionRepository.save(freeSession));
        Enrollment enrollment = savedSession.enroll(JAVAJIGI);
        long savedId = enrollmentRepository.save(enrollment);

        // when
        enrollmentRepository.delete(enrollmentRepository.findById(savedId));

        // then
        assertThat(sessionRepository.findById(savedSession.getId()).enrolledNumber())
                .isEqualTo(0);
    }
}
