package nextstep.courses.domain;

import nextstep.courses.exception.ExceedingMaximumStudentException;
import nextstep.courses.exception.NotEligibleRegistrationStatusException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionRegistrationTest {

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다.")
    void test01() {
        SessionRegistration session = new SessionRegistration(SessionStatus.RECRUITING, 1);

        assertThatNoException().isThrownBy(() -> session.register(NsUserTest.JAVAJIGI));
        assertThatThrownBy(() -> session.register(NsUserTest.SANJIGI))
                .isInstanceOf(ExceedingMaximumStudentException.class);
    }

    @Test
    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void test02() {
        SessionRegistration preparingSession = new SessionRegistration(SessionStatus.PREPARING, 1);
        SessionRegistration recruitingSession = new SessionRegistration(SessionStatus.RECRUITING, 1);
        SessionRegistration endedSession = new SessionRegistration(SessionStatus.ENDED, 1);

        assertThatNoException()
                .isThrownBy(() -> recruitingSession.register(NsUserTest.JAVAJIGI));
        assertThatThrownBy(() -> preparingSession.register(NsUserTest.SANJIGI))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
        assertThatThrownBy(() -> endedSession.register(NsUserTest.SANJIGI))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
    }

}
