package nextstep.courses.domain.enrollment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentStatusTest {

    @Test
    public void 설명으로_상태_조회한다() {
        EnrollmentStatus pending = EnrollmentStatus.from("대기중");

        assertThat(pending).isEqualTo(EnrollmentStatus.PENDING);
    }

    @Test
    public void 잘못된_설명으로_조회시_예외발생() {
        assertThatThrownBy(() -> EnrollmentStatus.from("잘못된상태"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 수강신청 상태입니다");
    }

    @ParameterizedTest
    @CsvSource({
            "APPROVED, true",
            "PENDING, false",
            "CANCELLED, false"
    })
    public void 승인_여부를_확인한다(EnrollmentStatus status, boolean isApproved) {
        assertThat(status.isApproved()).isEqualTo(isApproved);
    }

    @ParameterizedTest
    @CsvSource({
            "PENDING, true",
            "APPROVED, false",
            "CANCELLED, false"
    })
    public void 대기중_상태를_확인한다(EnrollmentStatus status, boolean isPending) {
        assertThat(status.isPending()).isEqualTo(isPending);
    }
}
