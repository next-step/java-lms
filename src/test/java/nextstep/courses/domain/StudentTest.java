package nextstep.courses.domain;

import nextstep.courses.CannotApproveException;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTest.FREE_SESSION_1;
import static nextstep.courses.domain.SessionTest.FREE_SESSION_2;
import static nextstep.users.domain.NsUserTest.TEACHER_1;
import static nextstep.users.domain.NsUserTest.TEACHER_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StudentTest {
    public static final Student STUDENT_1 = new Student(1L, 1L, EnrollmentStatus.WAITING);
    public static final Student STUDENT_2 = new Student(1L, 6L, EnrollmentStatus.WAITING);

    @Test
    void 수강신청_승인() {
        assertDoesNotThrow(() -> STUDENT_1.updateEnrollmentStatus(FREE_SESSION_1, TEACHER_1, EnrollmentStatus.APPROVED));
        assertThat(FREE_SESSION_1.sessionCondition().userNumber()).isEqualTo(1);

        assertThatExceptionOfType(CannotApproveException.class)
                .isThrownBy(() -> STUDENT_1.updateEnrollmentStatus(FREE_SESSION_1, TEACHER_2, EnrollmentStatus.APPROVED))
                .withMessage("강사 정보가 일치하지 않습니다.");
        assertThatExceptionOfType(CannotApproveException.class)
                .isThrownBy(() -> STUDENT_2.updateEnrollmentStatus(FREE_SESSION_2, TEACHER_1, EnrollmentStatus.APPROVED))
                .withMessage("인원을 추가로 승인할 수 없습니다.");
    }

    @Test
    void 수강신청_취소() {
        assertDoesNotThrow(() -> STUDENT_1.updateEnrollmentStatus(FREE_SESSION_1, TEACHER_1, EnrollmentStatus.REJECTED));
        assertThatExceptionOfType(CannotApproveException.class)
                .isThrownBy(() -> STUDENT_2.updateEnrollmentStatus(FREE_SESSION_1, TEACHER_2, EnrollmentStatus.REJECTED))
                .withMessage("강사 정보가 일치하지 않습니다.");
        assertDoesNotThrow(() -> STUDENT_2.updateEnrollmentStatus(FREE_SESSION_2, TEACHER_1, EnrollmentStatus.REJECTED));

    }
}
