package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentStatusTest {

    @DisplayName("수강신청의 승인 여부를 알 수 있다.")
    @Test
    void isApprovedTest() {
        assertThat(EnrollmentStatus.APPROVED.isApproved()).isTrue();
        assertThat(EnrollmentStatus.PENDING.isApproved()).isFalse();
        assertThat(EnrollmentStatus.REJECTED.isApproved()).isFalse();
    }

    @DisplayName("수강신청의 취소 여부를 알 수 있다.")
    @Test
    void isRejectedTest() {
        assertThat(EnrollmentStatus.APPROVED.isRejected()).isFalse();
        assertThat(EnrollmentStatus.PENDING.isRejected()).isFalse();
        assertThat(EnrollmentStatus.REJECTED.isRejected()).isTrue();
    }

}