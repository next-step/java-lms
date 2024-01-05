package nextstep.courses.domain;

import nextstep.courses.TeacherNotMatchException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static nextstep.courses.domain.SessionStatusTest.RECRUITING_SESSION_STATUS;
import static nextstep.users.domain.NsUserTest.TEACHER_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionTest {

    public static final Session FREE_SESSION_1 = makeSession(new SessionCondition(0L, 100L, 0L));
    public static final Session FREE_SESSION_2 = makeSession(new SessionCondition(0L, 2L, 2L));
    public static final Session FREE_SESSION_1_UPDATED = makeSession(new SessionCondition(0L, 100L, 1L));
    public static final Session PAID_SESSION = makeSession(new SessionCondition(800_000L, 120L, 0L));

    private static Session makeSession(SessionCondition sessionPaymentCondition) {
        return new Session(1L, 1L, 1L, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition, true, 3L);
    }

    @Test
    @DisplayName("생성_null 이거나 0_throw IllegalArgumentException")
    void 생성_validate() {
        SessionCondition sessionPaymentCondition = new SessionCondition(0L, 0L, 0L);
        assertThatThrownBy(() -> new Session(0L, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition, true, 3L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        assertThatThrownBy(() -> new Session(null, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition, true, 3L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
    }

    @Test
    @DisplayName("matchTeacher_강사정보_NsUser id 불칠치 시 throw CannotApproveException")
    void matchTeacher() {
        assertThatThrownBy(() -> FREE_SESSION_1.matchTeacher(TEACHER_2))
                .isInstanceOf(TeacherNotMatchException.class)
                .hasMessage("강사 정보가 일치하지 않습니다.");
    }
}
