package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.courses.domain.session.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class SessionStatusTest {
    public static final SessionStatus CLOSED_SESSION_STATUS_1 = new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.CLOSED);
    public static final SessionStatus CLOSED_SESSION_STATUS_2 = new SessionStatus(SessionProgressStatus.IN_PROGRESS, SessionRecruitmentStatus.CLOSED);
    public static final SessionStatus RECRUITING_SESSION_STATUS = new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

    @Test
    void 생성() {
        assertThatThrownBy(() -> new SessionStatus(SessionProgressStatus.FINISHED, SessionRecruitmentStatus.RECRUITING))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료된 강의는 모집할 수 없습니다.");
    }

    @Test
    @DisplayName("canEnroll_SessionProgressStatus, SessionRecruitmentStatus_throw CannotEnrollException")
    void canEnroll() {
        assertThatThrownBy(CLOSED_SESSION_STATUS_1::canEnroll)
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("강의가 모집중인 상태가 아닙니다.");
        assertThatThrownBy(CLOSED_SESSION_STATUS_2::canEnroll)
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("강의가 모집중인 상태가 아닙니다.");
        assertDoesNotThrow(RECRUITING_SESSION_STATUS::canEnroll);
    }
}
