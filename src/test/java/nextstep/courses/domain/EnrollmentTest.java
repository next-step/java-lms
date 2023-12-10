package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {
    @Test
    @DisplayName("수강신청 상태가 아닐 때 수강신청을하면 예외가 발생한다")
    void preparing_exception() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(SessionStatus.PREPARING, 15).enroll(NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("수강신청 상태가 아닐 때 수강신청을하면 예외가 발생한다")
    void capacity_exception() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI);
            enrollment.enroll(NsUserTest.SANJIGI);
        });
    }

    @Test
    @DisplayName("이미 수강신청을한 인원은 수강신청을 할 수 없다")
    void student_exception() {
        List<NsUser> students = Arrays.asList(NsUserTest.JAVAJIGI);
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 2, students);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI);
        });
    }
}
