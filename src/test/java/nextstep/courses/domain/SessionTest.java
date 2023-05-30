package nextstep.courses.domain;

import static nextstep.courses.domain.SessionPeriodTest.*;
import static nextstep.courses.domain.SessionPersonnelTest.sessionPersonalMax;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SessionTest {

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"Preparing", "Closed"})
    void 모집중_제외_register_불가(SessionStatus status) {
        Session session = SessionBuilder.aSession()
            .withSessionPeriod(sessionPeriodMinMax)
            .withSessionStatus(status)
            .withSessionPersonnel(sessionPersonalMax)
            .build();

        assertThatThrownBy(() -> session.register(userA(), LocalDateTime.now()))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("모집중이 아닙니다.");
    }

    @Test
    void 강의_신청_기간_예외() {
        Session session =
            SessionBuilder.aSession()
                .withSessionPeriod(
                    new SessionPeriod(LocalDateTime.of(2022, 10, 19, 0, 0), LocalDateTime.MAX))
                .withSessionStatus(SessionStatus.Recruiting)
                .withSessionPersonnel(sessionPersonalMax)
                .build();

        assertThatThrownBy(() -> session.register(userA(), LocalDateTime.of(2022, 10, 18, 0, 0)))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("강의 신청 기간이 아닙니다.");
    }

    private static NsUser userA() {
        return new NsUser(1L, "A", "A", "A", "A@A.A");
    }

}
