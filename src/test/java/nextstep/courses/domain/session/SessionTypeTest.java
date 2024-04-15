package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class SessionTypeTest {
    public static final SessionType PAID_SESSION_TYPE = new SessionType(2, 100);
    public static final SessionType FREE_SESSION_TYPE = SessionType.freeSessionType();

    @Nested
    @DisplayName("SessionType 생성 테스트")
    class InstanceCreationTest {
        @ParameterizedTest
        @CsvSource(value = {"-1:800000", "80:-1"}, delimiter = ':')
        @DisplayName("maxNumberOfEnrollment 또는 fee가 0보다 작은 경우 IllegalArgumentException이 발생한다.")
        void testFailCase(int maxNumberOfEnrollment, int fee) {
            assertThatThrownBy(() -> new SessionType(maxNumberOfEnrollment, fee))
                    .isExactlyInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("검증 조건을 통과한 경우 SessionType을 생성한다.")
        void testSuccessCase() {
            assertThatNoException().isThrownBy(() -> new SessionType(80, 800000));
            assertThatNoException().isThrownBy(SessionType::freeSessionType);
        }
    }

    @Nested
    @DisplayName("isEnrollmentPossible() 테스트")
    class IsEnrollmentPossibleTest {
        @ParameterizedTest
        @CsvSource(value = {"2:100:false", "1:50:false", "1:100:true"}, delimiter = ':')
        @DisplayName("유료 강의의 경우 numberOfCurrentEnrollment < maxNumberOfEnrollment 이면서 Payment.amount가 fee와 동일한 경우 true를 그렇지 않은 경우 false를 반환한다.")
        void testPaidSessionType(int numberOfCurrentEnrollment, long amountOfPayment, boolean expected) {
            SessionType paidSessionType = new SessionType(2, 100);
            assertThat(paidSessionType.isEnrollmentPossible(numberOfCurrentEnrollment, new Payment("p1", 2L, 3L, amountOfPayment))).isEqualTo(expected);
        }

        @Test
        @DisplayName("무료 강의의 경우 numberOfCurrentEnrollment와 Payment.amount에 상관 없이 항상 true를 반환한다.")
        void testFreeSessionType() {
            SessionType freeSessionType = SessionType.freeSessionType();
            assertThat(freeSessionType.isEnrollmentPossible(100, new Payment())).isTrue();
        }
    }
}