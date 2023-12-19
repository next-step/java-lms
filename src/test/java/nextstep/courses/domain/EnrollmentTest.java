package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EnrollmentTest {
    @Test
    @DisplayName("강의 최대 수강 인원을 초과하면 예외가 던져진다")
    void charge_session_capacity_exception() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        NsUser sanjigi = NsUserTest.SANJIGI;

        Enrollment enrollment = new Enrollment(SessionState.RECRUITING, 1);
        enrollment.enroll(javajigi);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(sanjigi);
        });
    }

    @Test
    @DisplayName("수강생이 똑같은 강의를 수강신청하면 예외가 던져진다")
    void duplicate_exception() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Enrollment enrollment = new Enrollment(SessionState.RECRUITING, 2);
        enrollment.enroll(javajigi);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(javajigi);
        });
    }

    @Test
    @DisplayName("모집중이 아닌 강의를 수강신청하면 예외가 던져진다")
    void recruiting_exception() {
        NsUser javajigi = NsUserTest.JAVAJIGI;
        Enrollment enrollment = new Enrollment(SessionState.END, 2);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(javajigi);
        });
    }
}
