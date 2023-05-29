package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class EnrollmentTest {
    @Test
    void 수강신청_성공() throws AlreadyEnrollmentException {
        Enrollment before = new Enrollment(10, SessionStatus.ENROLLING);
        before.enroll(NsUserTest.JAVAJIGI);
    }

    @Test
    void 수강신청_상태가_아닐_때_수강신청() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(10, SessionStatus.PREPARING)
                        .enroll(NsUserTest.JAVAJIGI));
    }

    @Test
    void 수강인원_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Enrollment enrollment = new Enrollment(1, SessionStatus.ENROLLING);
            enrollment.enroll(NsUserTest.JAVAJIGI);
            enrollment.enroll(NsUserTest.SANJIGI);
        });
    }
}
