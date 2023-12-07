package nextstep.lms.infrastructure;

import nextstep.lms.domain.Session;
import nextstep.lms.enums.PricingTypeEnum;
import nextstep.lms.enums.SessionStatusEnum;
import nextstep.lms.repository.CoverImageRepository;
import nextstep.lms.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void create_read() {
        Session session = new Session(coverImageRepository.findById(1L),
                PricingTypeEnum.PAID.name(),
                800_000L,
                SessionStatusEnum.PREPARING.name(),
                100,
                LocalDateTime.of(2023, 12, 7, 00, 00),
                LocalDateTime.of(2023, 12, 31, 23, 59));
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getImageId()).isEqualTo(savedSession.getImageId());
        assertThat(session.getPricingType()).isEqualTo(savedSession.getPricingType());
        assertThat(session.getTuitionFee()).isEqualTo(savedSession.getTuitionFee());
        assertThat(session.getSessionStatus()).isEqualTo(savedSession.getSessionStatus());
        assertThat(session.getCapacity()).isEqualTo(savedSession.getCapacity());
        assertThat(session.getStartDate()).isEqualTo(savedSession.getStartDate());
        assertThat(session.getEndDate()).isEqualTo(savedSession.getEndDate());
    }
}