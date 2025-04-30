package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidPaymentPolicyTest {

    @Test
    @DisplayName("유효한 수강료와 최대 수강 인원으로 PaidPaymentPolicy를 생성할 수 있다")
    void createPaidPaymentPolicy() {
        PaidPaymentPolicy policy = new PaidPaymentPolicy(10000L, 30);

        assertThat(policy.enrollmentLimit()).isEqualTo(30);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 30, 유료강의의 수강료는 0보다 커야 합니다",
        "-1000, 30, 유료강의의 수강료는 0보다 커야 합니다"
    })
    @DisplayName("수강료가 0 이하인 경우 예외가 발생한다")
    void throwExceptionWhenFeeIsNotPositive(long fee, int enrollmentLimit, String expectedMessage) {
        assertThatThrownBy(() -> new PaidPaymentPolicy(fee, enrollmentLimit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "10000, 0, 유료 강의는 최대 수강 인원이 0보다 커야 합니다",
        "10000, -5, 유료 강의는 최대 수강 인원이 0보다 커야 합니다"
    })
    @DisplayName("최대 수강 인원이 0 이하인 경우 예외가 발생한다")
    void throwExceptionWhenEnrollmentLimitIsNotPositive(long fee, int enrollmentLimit, String expectedMessage) {
        assertThatThrownBy(() -> new PaidPaymentPolicy(fee, enrollmentLimit))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    @DisplayName("현재 수강생 수가 최대 수강 인원보다 작으면 수강 신청이 가능하다")
    void canEnrollWhenStudentCountIsLessThanLimit() {
        PaidPaymentPolicy policy = new PaidPaymentPolicy(10000L, 30);
        int currentStudentCount = 25;

        boolean result = policy.canEnroll(currentStudentCount);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("현재 수강생 수가 최대 수강 인원과 같으면 수강 신청이 불가능하다")
    void cannotEnrollWhenStudentCountEqualsLimit() {
        PaidPaymentPolicy policy = new PaidPaymentPolicy(10000L, 30);
        int currentStudentCount = 30;

        boolean result = policy.canEnroll(currentStudentCount);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("결제 금액이 수강료와 일치하면 검증을 통과한다")
    void validateEnrollmentWhenAmountMatchesFee() {
        long fee = 10000L;
        PaidPaymentPolicy policy = new PaidPaymentPolicy(fee, 30);

        policy.validateEnrollment(fee);
    }

    @Test
    @DisplayName("결제 금액이 수강료와 일치하지 않으면 예외가 발생한다")
    void throwExceptionWhenAmountDoesNotMatchFee() {
        long fee = 10000L;
        long differentAmount = 15000L;
        PaidPaymentPolicy policy = new PaidPaymentPolicy(fee, 30);

        assertThatThrownBy(() -> policy.validateEnrollment(differentAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다");
    }
}