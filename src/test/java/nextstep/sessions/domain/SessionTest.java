package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatCode;

public class SessionTest {
    private Session freeSession;

    @BeforeEach
    void setup() {
        freeSession = Session.freeSession(1L, "무료강의", PeriodTest.APRIL, CoverImageTest.TEST_IMAGE, SessionStatus.RECRUITING, new NsUsers(), LocalDateTime.now(), LocalDateTime.now());
    }

    @DisplayName("수강신청 가능여부 검증 메서드는")
    @Nested
    class Describe_assertCanEnroll {

        @DisplayName("모집중이고, 신청한 적이 없고, 자리가 있을 경우 통과한다.")
        @Test
        void can_enroll() {
            assertThatCode(() -> freeSession.assertCanEnroll(NsUserTest.JAVAJIGI, LocalDate.of(2024, 4, 15).atStartOfDay()))
                    .doesNotThrowAnyException();
        }

    }
}
