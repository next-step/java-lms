package nextstep.courses.service;

import nextstep.courses.infrastructure.JdbcCandidateRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
class SessionServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionService sessionService;

    @BeforeEach
    void setUp() {
        sessionService = new SessionService(
                new JdbcSessionRepository(jdbcTemplate),
                new JdbcStudentRepository(jdbcTemplate),
                new JdbcCandidateRepository(jdbcTemplate));
    }

    @DisplayName("사용자 수강신청 테스트 - 이미 수강신청한 사용자는 중복 수강신청할 수 없다.")
    @Test
    void register_중복수강신청() {
        assertThatThrownBy(() -> sessionService.registerSession(0L, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("수강신청 승인 테스트 - 수강신청한 사용자가 아닌 경우")
    @Test
    void approve_미수강_사용자() {
        assertThatThrownBy(() -> sessionService.approveSessionRegister(0L, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청 승인 테스트 - 선발된 사용자가 아닌 경우")
    @Test
    void approve_미선발_사용자() {
        sessionService.registerSession(0L, NsUserTest.SANJIGI);
        assertThatThrownBy(() -> sessionService.approveSessionRegister(0L, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
