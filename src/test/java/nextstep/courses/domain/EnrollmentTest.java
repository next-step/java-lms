package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class EnrollmentTest {
    @Test
    @DisplayName("수강신청 상태가 아닐 때 수강신청을하면 예외가 발생한다")
    void preparing_exception() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(SessionStatus.PREPARING, 15).enroll(NsUserTest.JAVAJIGI));
    }
}
