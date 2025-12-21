package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EnrollmentTest {

    @Test
    void 등록_정보가_같다면_같은_Enrollment라고_판단한다() {
        assertThat(new Enrollment(1L, 1L)).isEqualTo(new Enrollment(1L, 1L));
    }

    @Test
    void 등록_정보가_다르다면_다른_Enrollment라고_판단한다() {
        assertThat(new Enrollment(1L, 1L)).isNotEqualTo(new Enrollment(1L, 2L));
    }
}
