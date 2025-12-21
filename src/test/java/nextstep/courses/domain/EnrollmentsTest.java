package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsTest {
    @Test
    void Enrollments_sameUserId() {
        Enrollments enrollments = new Enrollments(new Capacity(2));
        enrollments.add(1L, 10L);

        assertThatThrownBy(() -> enrollments.add(1L, 10L))
                .isInstanceOf(IllegalStateException.class);
    }
}