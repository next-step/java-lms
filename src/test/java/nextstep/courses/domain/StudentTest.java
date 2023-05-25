package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {
    @Test
    void 수강승인() {
        Student student = createStudentWithApproved(false);
        student.approve();
        assertThat(student).isEqualTo(createStudentWithApproved(true));
    }

    @Test
    void 수강취소() {
        Student student = createStudentWithApproved(true);
        student.disApprove();
        assertThat(student).isEqualTo(createStudentWithApproved(false));
    }

    private static Student createStudentWithApproved(boolean approved) {
        return new Student(1L, 1L, approved);
    }
}
