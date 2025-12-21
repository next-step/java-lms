package nextstep.courses.domain.policy;

import static org.assertj.core.api.Assertions.assertThatCode;

import nextstep.courses.domain.value.Money;
import org.junit.jupiter.api.Test;

public class FreeSessionPolicyTest {

    @Test
    void 무료_정책의_경우에는_인원이나_금액에_관계없이_등록에_성공한다() {
        FreeSessionPolicy freeSessionPolicy = new FreeSessionPolicy();

        assertThatCode(() -> freeSessionPolicy.validate(new Money(0), 0))
                .doesNotThrowAnyException();
    }
}
