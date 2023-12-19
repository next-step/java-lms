package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnrollmentTest {
    @Test
    @DisplayName("강의 최대 수강 인원을 초과하면 예외가 던져진다")
    void charge_session_capacity_exception() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        NsUser sanjigi = NsUserTest.SANJIGI;

        Enrollment enrollment = new Enrollment(SessionState.RECRUITING, 1);
        enrollment.enroll(javajigi);
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(sanjigi);
        });
    }
}
