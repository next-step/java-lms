package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {
    @Test
    void 수강신청_성공() throws AlreadyEnrollmentException {
        Enrollment before = new Enrollment(SessionStatus.ENROLLING, 10);
        before.enroll(NsUserTest.JAVAJIGI);
    }

    @Test
    void 수강신청_상태가_아닐_때_수강신청() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(SessionStatus.PREPARING, 10)
                        .enroll(NsUserTest.JAVAJIGI));
    }

    @Test
    void 수강인원_초과() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI);
            enrollment.enroll(NsUserTest.SANJIGI);
        });
    }

    @Test
    void 수강신청_이미_수강신청한_학생() throws Exception {
        List<NsUser> students = Arrays.asList(NsUserTest.JAVAJIGI);
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 2, students);
        assertThatThrownBy(() -> enrollment.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }
}
