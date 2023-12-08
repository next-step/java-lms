package nextstep.courses.domain;

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
        SessionStudent sessionStudent = new SessionStudent(4, sessionStudents);
        SessionEnrollment sessionEnrollment = new SessionEnrollment(true, sessionStudent, new SessionPrice(0), SessionStatus.PREPARING);

        assertThatThrownBy(() -> sessionEnrollment.enrollFreeSession(new NsUser()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의는 모집중이 아닙니다");
    }

    @DisplayName("유료 강의가 준비중이라면 신청을 했을 때 예외를 던진다")
    @Test
    void notOpenPaySessionException() {
        SessionStudents sessionStudents = new SessionStudents(new ArrayList<>(List.of(SANJIGI, JAVAJIGI)));
        SessionStudent sessionStudent = new SessionStudent(4, sessionStudents);
        SessionEnrollment sessionEnrollment = new SessionEnrollment(false, sessionStudent, new SessionPrice(50000), SessionStatus.PREPARING);

        assertThatThrownBy(() -> sessionEnrollment.enrollPaySession(new NsUser(), 50000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의는 모집중이 아닙니다");
    }

}