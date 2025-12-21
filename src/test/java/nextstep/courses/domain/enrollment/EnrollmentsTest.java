package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class EnrollmentsTest {

    @Test
    void 수강신청을_이미_했을_때_추가로_신청한다면_예외가_발생한다() {
        Enrollments enrollments = new Enrollments(List.of(new Enrollment(1L, 1L)));

        assertThatThrownBy(() -> enrollments.add(new Enrollment(1L, 1L)))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 수강신청한_학생_아이디를_반환한다() {
        Enrollments enrollments = new Enrollments(List.of(
                new Enrollment(1L, 1L),
                new Enrollment(2L, 1L),
                new Enrollment(3L, 1L)
        ));

        assertThat(enrollments.studentIds()).containsExactly(1L, 2L, 3L);
    }
}
