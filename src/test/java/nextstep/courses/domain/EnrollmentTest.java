package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnrollmentTest {

    @Test
    void 신청하면_APPLIED_상태다() {
        Enrollment enrollment = new Enrollment(1L, 1L);

        assertThat(enrollment.getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.APPLIED.toString());
    }

    @Test
    void 선발된_신청자는_승인할_수_있다() {
        Enrollment enrollment = new Enrollment(1L, 1L);

        enrollment.select();
        enrollment.approve();

        assertThat(enrollment.getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.APPROVED.toString());
    }
}
