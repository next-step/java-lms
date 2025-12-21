package nextstep.courses.domain.policy;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.value.Money;
import org.junit.jupiter.api.Test;

public class PaidSessionPolicyTest {

    private final PaidSessionPolicy paidSessionPolicy = new PaidSessionPolicy(500_000, 50);

    @Test
    void 결제_금액이_일치하고_정원이_남아있으면_등록에_성공한다() {
        assertThatCode(() -> paidSessionPolicy.validate(new Money(500_000), 49))
                .doesNotThrowAnyException();
    }

    @Test
    void 금액이_수강료와_일치하지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> paidSessionPolicy.validate(new Money(450_000), 47))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다.");
    }

    @Test
    void 현재_수강_신청_인원이_정원_이상이면_예외가_발생한다() {
        assertThatThrownBy(() -> paidSessionPolicy.validate(new Money(500_000), 50))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("수강 인원이 초과되었습니다.");
    }
}
