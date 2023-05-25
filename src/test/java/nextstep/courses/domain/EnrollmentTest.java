package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class EnrollmentTest {
    @Test
    void 수강신청_성공() throws AlreadyEnrollmentException {
        Enrollment before = new Enrollment(10, SessionStatus.ENROLLING);
        before.enroll(new Student(1L, 1L), new ArrayList<>());
    }

    @Test
    void 수강신청_상태가_아닐_때_수강신청() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(10, SessionStatus.PREPARING)
                        .enroll(new Student(1L, 1L), new ArrayList<>()));
    }

    @Test
    void 수강인원_초과() {
        List<Student> students = Arrays.asList(new Student(1L, 1L));
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Enrollment enrollment = new Enrollment(1, SessionStatus.ENROLLING);
            enrollment.enroll(new Student(2L, 1L), students);
        });
    }
}
