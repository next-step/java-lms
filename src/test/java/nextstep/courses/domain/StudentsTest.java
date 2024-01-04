package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentsTest {

    public static final Student STUDENT_1 = new Student(1L, 1L);
    public static final Student STUDENT_2 = new Student(1L, 2L);
    public static final Student STUDENT_3 = new Student(1L, 5L);
    public static final Student STUDENT_4 = new Student(1L, 6L);
    public static final Student STUDENT_1_APPROVED = new Student(1L, 1L, EnrollmentStatus.APPROVED);
    public static final Student STUDENT_2_APPROVED = new Student(1L, 2L, EnrollmentStatus.APPROVED);
    public static final Student STUDENT_4_APPROVED = new Student(1L, 6L, EnrollmentStatus.APPROVED);
    public static final Student STUDENT_4_REJECTED = new Student(1L, 6L, EnrollmentStatus.REJECTED);
    public static final Students STUDENTS = new Students(STUDENT_1_APPROVED, STUDENT_2_APPROVED, STUDENT_3);

    @Test
    @DisplayName("approvedUserNumber_승인,대기 섞인 목록_승인된 갯수만 반환")
    void approvedUserNumber() {
        assertThat(STUDENTS.approvedUserNumber()).isEqualTo(2L);
    }
}
