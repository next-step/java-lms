package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTest.FREE_SESSION_1;
import static nextstep.courses.domain.SessionTest.FREE_SESSION_2;
import static nextstep.users.domain.NsUserTest.TEACHER_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StudentTest {
    public static final Student STUDENT_1 = new Student(1L, 1L, EnrollmentStatus.WAITING);
    public static final Student STUDENT_2 = new Student(1L, 6L, EnrollmentStatus.WAITING);

    @Test
    @DisplayName("updateEnrollmentStatus_정상 승인_enrollmentStatus가 APPROVED로 변경, 강의는 기존 수강인원 +1")
    void 수강신청_승인() {
        assertDoesNotThrow(() -> STUDENT_1.updateEnrollmentStatus(FREE_SESSION_1, TEACHER_1, EnrollmentStatus.APPROVED));
        assertThat(STUDENT_1.enrollmentStatus()).isEqualTo(EnrollmentStatus.APPROVED);
        assertThat(FREE_SESSION_1.sessionCondition().userNumber()).isEqualTo(1L);
    }

    @Test
    @DisplayName("updateEnrollmentStatus_정성 취소_enrollmentStatus가 REJECTED로 변경, 강의는 기존 수강인원 유지")
    void 수강신청_취소() {
        assertDoesNotThrow(() -> STUDENT_2.updateEnrollmentStatus(FREE_SESSION_2, TEACHER_1, EnrollmentStatus.REJECTED));
        assertThat(STUDENT_2.enrollmentStatus()).isEqualTo(EnrollmentStatus.REJECTED);
        assertThat(FREE_SESSION_2.sessionCondition().userNumber()).isEqualTo(2L);
    }
}
