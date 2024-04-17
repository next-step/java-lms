package nextstep.courses.domain.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionStudentFixture.student;
import static nextstep.courses.domain.fixture.SessionStudentsFixture.students;
import static nextstep.courses.domain.student.StudentEnrollmentStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionStudentsTest {

    @Test
    @DisplayName("[성공] 수강생 신청 상태를 승인 상태로 수정한다.")
    void 수강생_승인_상태_수정() {
        SessionStudent student1 = student(1L, SESSION_ID, 1L, PENDING);
        SessionStudent student2 = student(2L, SESSION_ID, 2L, PENDING);
        SessionStudents students = students(student1, student2);

        students.toApproveStatus();

        assertThat(student1.getEnrollmentStatus()).isEqualTo(APPROVAL);
        assertThat(student2.getEnrollmentStatus()).isEqualTo(APPROVAL);
    }

    @Test
    @DisplayName("[성공] 수강생 신청 상태를 취소 상태로 수정한다.")
    void 수강생_취소_상태_수정() {
        SessionStudent student1 = student(1L, SESSION_ID, 1L, PENDING);
        SessionStudent student2 = student(2L, SESSION_ID, 2L, PENDING);
        SessionStudents students = students(student1, student2);

        students.toCancelStatus();

        assertThat(student1.getEnrollmentStatus()).isEqualTo(CANCEL);
        assertThat(student2.getEnrollmentStatus()).isEqualTo(CANCEL);
    }
}
