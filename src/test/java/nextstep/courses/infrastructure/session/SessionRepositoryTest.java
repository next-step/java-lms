package nextstep.courses.infrastructure.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void init() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("새로운 강의를 생성한다.")
    void Save_NewSession() {
        final SessionEntity sessionEntity = new SessionEntity(
                "Java",
                "모집중",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                "유료",
                10000,
                50,
                10
        );

        final long savedSessionEntityId = sessionRepository.save(sessionEntity);
        final SessionEntity savedSessionEntity = sessionRepository.findById(savedSessionEntityId).get();

        assertThat(savedSessionEntity.id())
                .isEqualTo(savedSessionEntityId);
    }
}
