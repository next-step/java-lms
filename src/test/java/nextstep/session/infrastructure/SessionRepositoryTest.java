package nextstep.session.infrastructure;

import nextstep.session.domain.AdmissionRepository;
import nextstep.session.domain.EnrollmentRepository;
import nextstep.session.domain.FreeSession;
import nextstep.session.domain.PaidSession;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionImageRepository;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.fixture.SessionImageFixture;
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
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionImageRepository sessionImageRepository;
    private EnrollmentRepository enrollmentRepository;
    private AdmissionRepository admissionRepository;
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        admissionRepository = new JdbcAdmissionRepository(jdbcTemplate);

        sessionRepository = new JdbcSessionRepository(jdbcTemplate, sessionImageRepository, enrollmentRepository, admissionRepository);
    }

    @Test
    @DisplayName("생성 / 무료강의 / 성공")
    void createFreeSession() {
        // given
        Session freeSession = new FreeSession(1L, LocalDate.now(), LocalDate.now().plusDays(1), SessionImageFixture.createSessionImage());

        // when
        Long id = sessionRepository.save(freeSession);

        // then
        Session savedSession = sessionRepository.findById(id);
        assertThat(savedSession.getSessionType()).isEqualTo(SessionType.FREE);
        assertThat(savedSession.getSessionImage()).isNotNull();
    }

    @Test
    @DisplayName("생성 / 유료강의 / 성공")
    void createPaidSession() {
        // given
        Session paidSession = new PaidSession(1L, LocalDate.now(), LocalDate.now().plusDays(1), SessionImageFixture.createSessionImage(), 3, 10000L);

        // when
        Long id = sessionRepository.save(paidSession);

        // then
        Session savedSession = sessionRepository.findById(id);
        assertThat(savedSession.getSessionType()).isEqualTo(SessionType.PAID);
        assertThat(savedSession.getSessionImage()).isNotNull();
    }

}
