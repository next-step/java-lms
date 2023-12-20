package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EnrollmentTest {
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
