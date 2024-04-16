package nextstep.courses.infrastructure;

import static nextstep.courses.domain.SessionCoverImageTest.SAMPLE_COVER_IMAGE;
import static nextstep.courses.domain.SessionCoverImageTest.SAMPLE_COVER_IMAGE2;
import static nextstep.qna.domain.TestFixtures.FIXED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("Session이 성공적으로 저장된다")
    void save_session() {
        Session session = new Session(FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1), List.of(SAMPLE_COVER_IMAGE, SAMPLE_COVER_IMAGE2), FIXED_DATE_TIME);

        sessionRepository.save(session);
        Session savedSession = sessionRepository.findById(session.getId());

        assertThat(savedSession.getStartDate()).isEqualTo(session.getStartDate());
        assertThat(savedSession.getEndDate()).isEqualTo(session.getEndDate());
        assertThat(savedSession.getCreatedAt()).isEqualTo(session.getCreatedAt());
        assertThat(savedSession.getCoverImages()).containsAll(session.getCoverImages());
    }

    @Test
    @DisplayName("PaidSession이 성공적으로 저장된다")
    void save_paid_session() {
        PaidSession paidSession = new PaidSession(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            List.of(SAMPLE_COVER_IMAGE, SAMPLE_COVER_IMAGE2), SessionStatus.RECRUIT, true, 800_000L, 1,
            FIXED_DATE_TIME
        );

        sessionRepository.save(paidSession);
        PaidSession savedSession = (PaidSession) sessionRepository.findById(paidSession.getId());

        assertThat(savedSession.getStartDate()).isEqualTo(paidSession.getStartDate());
        assertThat(savedSession.getEndDate()).isEqualTo(paidSession.getEndDate());
        assertThat(savedSession.getStatusName()).isEqualTo(paidSession.getStatusName());
        assertThat(savedSession.getPrice()).isEqualTo(paidSession.getPrice());
        assertThat(savedSession.getCapacity()).isEqualTo(paidSession.getCapacity());
        assertThat(savedSession.getCreatedAt()).isEqualTo(paidSession.getCreatedAt());
        assertThat(savedSession.getCoverImages()).containsAll(paidSession.getCoverImages());
    }

    @Test
    @DisplayName("id로 저장된 엔티티를 성공적으로 불러온다")
    void findById() {
        assertThatThrownBy(() -> sessionRepository.findById(0L))
            .isInstanceOf(EmptyResultDataAccessException.class);

        Session session = new Session(FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1), List.of(SAMPLE_COVER_IMAGE, SAMPLE_COVER_IMAGE2), FIXED_DATE_TIME);
        sessionRepository.save(session);

        assertThatNoException()
            .isThrownBy(() -> sessionRepository.findById(0L));
    }
}
