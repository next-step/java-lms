package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @DisplayName("강의의 수강생 일치여부를 알 수 있다.")
    @Test
    void matchStudentTest() {
        Student student1 = Student.of(1L, 1L);
        Student student2 = Student.of(1L, 1L);
        Student student3 = Student.of(2L, 1L);
        Student student4 = Student.of(1L, 2L);

        assertThat(student1.matchStudents(student2)).isTrue();
        assertThat(student1.matchStudents(student3)).isFalse();
        assertThat(student1.matchStudents(student4)).isFalse();
    }

    @DisplayName("수강생의 수강신청 승인 여부를 알 수 있다.")
    @Test
    void isApprovedTest() {
        Student approvedUser = Student.of(1L, 1L, EnrollmentStatus.APPROVED);
        Student rejectedUser = Student.of(2L, 1L, EnrollmentStatus.REJECTED);

        assertThat(approvedUser.isApproved()).isTrue();
        assertThat(rejectedUser.isApproved()).isFalse();
    }


    @DisplayName("수강생의 수강신청 취소 여부를 알 수 있다.")
    @Test
    void isRejectedTest() {
        Student approvedUser = Student.of(1L, 1L, EnrollmentStatus.APPROVED);
        Student rejectedUser = Student.of(2L, 1L, EnrollmentStatus.REJECTED);

        assertThat(approvedUser.isRejected()).isFalse();
        assertThat(rejectedUser.isRejected()).isTrue();
    }
}