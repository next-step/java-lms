package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionEnrollmentTest {

    @DisplayName("무료 강의가 준비중이라면 신청을 했을 때 예외를 던진다")
    @Test
    void notOpenFreeSessionException() {
        SessionStudents sessionStudents = new SessionStudents(new ArrayList<>(List.of(SANJIGI, JAVAJIGI)));
        SessionEnrollment sessionEnrollment = new SessionEnrollment(true, 2, new SessionPrice(0L), SessionStatus.PREPARING, sessionStudents);

        assertThatThrownBy(() -> sessionEnrollment.enrollFreeSession(new NsUser()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의는 모집중이 아닙니다");
    }

    @DisplayName("유료 강의가 준비중이라면 신청을 했을 때 예외를 던진다")
    @Test
    void notOpenPaySessionException() {
        SessionStudents sessionStudents = new SessionStudents(new ArrayList<>(List.of(SANJIGI, JAVAJIGI)));
        SessionEnrollment sessionEnrollment = new SessionEnrollment(false, 4, new SessionPrice(50000L), SessionStatus.PREPARING, sessionStudents);

        assertThatThrownBy(() -> sessionEnrollment.enrollPaySession(new NsUser(), 50000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의는 모집중이 아닙니다");
    }

    @DisplayName("유로 강의의 수강 제한 인원을 넘어서면 예외를 던진다")
    @Test
    void aboveMaxSessionSize() {
        SessionStudents sessionStudents = new SessionStudents(new ArrayList<>(List.of(SANJIGI, JAVAJIGI)));
        SessionEnrollment sessionEnrollment = new SessionEnrollment(false, 2, new SessionPrice(50000L), SessionStatus.OPEN, sessionStudents);

        assertThatThrownBy(() -> sessionEnrollment.enrollPaySession(new NsUser(), 50000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 인원이 다 찼습니다");
    }
}