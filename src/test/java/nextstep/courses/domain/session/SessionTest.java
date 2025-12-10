package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import nextstep.courses.domain.registration.Registrations;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void 모집중일때_수강신청_가능() {
        Session session = aSession().build();
        Registrations registrations = new Registrations();
        Enrollment enrollment = session.enrollment(registrations);

        assertDoesNotThrow(() -> enrollment.enroll(0, 1L));
    }

    @Test
    void 모집중이_아닐때_수강신청하면_예외() {
        Session session = aSession().build();
        Registrations registrations = new Registrations();
        Enrollment enrollment = session.enrollment(registrations);


        enrollment.stopRecruitment();

        assertThatThrownBy(() -> enrollment.enroll(0, 1L))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 준비중이_아닐때_강의시작하면_예외() {
        Session session = aSession().withState(SessionProgressState.IN_PROGRESS).build();

        assertThatThrownBy(() -> session.start())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("준비중인 강의만 시작할 수 있습니다.");
    }

    @Test
    void 진행중이_아닐때_종료하면_예외() {
        Session session = aSession().build();

        assertThatThrownBy(() -> session.finish())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("진행중인 강의만 종료할 수 있습니다.");
    }

    @Test
    void 유료강의_수강료_불일치시_예외() {
        Session session = aSession()
            .paid(10000L, 10)
            .build();
        Registrations registrations = new Registrations();
        Enrollment enrollment = session.enrollment(registrations);

        assertThatThrownBy(() -> enrollment.enroll(5000L, 1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강료와 지불한 금액이 정확히 일치해야 합니다.");
    }
}
