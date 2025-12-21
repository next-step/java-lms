package nextstep.courses.domain;

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
}
