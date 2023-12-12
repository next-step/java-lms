package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.sessionuser.SessionUser;
import nextstep.courses.domain.sessionuser.SessionUserRepository;
import nextstep.courses.domain.sessionuser.SessionUsers;
import nextstep.courses.domain.sessionuser.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@JdbcTest
class SessionUserRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionUserRepository sessionUserRepository;

    @BeforeEach
    void setUp() {
        sessionUserRepository = new JdbcSessionUserRepository(jdbcTemplate);
    }

    @DisplayName("저장 및 조회 쿼리 테스트")
    @Test
    void crud() {
        // given
        SessionUser sessionuser = new SessionUser(1L, 1L, UserType.TUTOR);
        sessionUserRepository.save(sessionuser);
        // when
        SessionUsers result = sessionUserRepository.findBySession(new Session(1L, 1L, SessionStatus.PREPARE, LocalDateTime.now(), LocalDateTime.now(), false, 10));
        // then
        Assertions.assertThat(result.notCanceledUserSize()).isEqualTo(1);
    }

}