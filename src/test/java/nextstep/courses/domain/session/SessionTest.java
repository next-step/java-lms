package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.SessionBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import nextstep.courses.domain.registration.Registrations;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void 모집중일때_수강신청_가능() {
        Session session = aSession().recruiting().build();
      Registrations registrations = new Registrations();
      Enrollment enrollment = session.enrollment(registrations);

      assertDoesNotThrow(() -> enrollment.enroll(0, 1L));
    }

    @Test
    void 모집중이_아닐때_수강신청하면_예외() {
        Session session = aSession().build();
      Registrations registrations = new Registrations();

      assertThatThrownBy(() -> session.enrollment(registrations))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("모집중인 강의만 수강신청이 가능합니다.");
    }

    @Test
    void 준비중이_아닐때_모집시작하면_예외() {
        Session session = aSession().recruiting().build();

        assertThatThrownBy(() -> session.open())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("준비중인 강의만 모집을 시작할 수 있습니다.");
    }

    @Test
    void 모집중이_아닐때_종료하면_예외() {
        Session session = aSession().build();

        assertThatThrownBy(() -> session.close())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("모집중인 강의만 종료할 수 있습니다.");
    }

    @Test
    void 유료강의_수강료_불일치시_예외() {
        Session session = aSession()
            .recruiting()
            .paid(10000L, 10)
            .build();
      Registrations registrations = new Registrations();
      Enrollment enrollment = session.enrollment(registrations);

      assertThatThrownBy(() -> enrollment.enroll(5000L, 1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강료와 지불한 금액이 정확히 일치해야 합니다.");
    }
}
