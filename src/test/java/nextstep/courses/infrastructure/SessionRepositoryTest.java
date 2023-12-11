package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("저장 및 조회 쿼리 테스트")
    @Test
    void crud() {
        // given
        Session session = new Session(1L, SessionStatus.PREPARE, LocalDateTime.now(), LocalDateTime.now().plusDays(5), false, 10);
        Long savedId = sessionRepository.save(session);
        // when
        Session savedSession = sessionRepository.findById(savedId);
        // then
        Assertions.assertThat(savedSession.getId()).isEqualTo(savedId);
    }
}