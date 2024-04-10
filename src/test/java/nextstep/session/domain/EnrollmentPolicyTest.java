package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.session.InvalidEnrollmentPolicyException;
import org.junit.jupiter.api.Test;

class EnrollmentPolicyTest {

    @Test
    public void 최소_수강_인원을_충족해야한다() {
        assertThatThrownBy(() -> {
            EnrollmentPolicy.createPaidSession(-1, -1);
        }).isInstanceOf(InvalidEnrollmentPolicyException.class)
            .hasMessageContaining("최소수강인원은 0명 이상, 최소 수강료는 0원 이상이여야 합니다.");
    }

    @Test
    public void 무료_등록_정책을_생성() throws InvalidEnrollmentPolicyException {
        EnrollmentPolicy enrollmentPolicy = EnrollmentPolicy.createFreeSession();

        assertThat(enrollmentPolicy.getFee()).isEqualTo(0);
        assertThat(enrollmentPolicy.getPriceType()).isEqualTo(PriceType.FREE);
        assertThat(enrollmentPolicy.getMaxEnrollment()).isEqualTo(0);
    }

    @Test
    public void 유료_등록_정책_생성() throws InvalidEnrollmentPolicyException {
        EnrollmentPolicy enrollmentPolicy = EnrollmentPolicy.createPaidSession(100, 50000);

        assertThat(enrollmentPolicy.getFee()).isEqualTo(50000);
        assertThat(enrollmentPolicy.getPriceType()).isEqualTo(PriceType.PAID);
        assertThat(enrollmentPolicy.getMaxEnrollment()).isEqualTo(100);
    }

}
