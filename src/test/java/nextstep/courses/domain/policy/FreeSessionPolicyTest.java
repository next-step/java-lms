package nextstep.courses.domain.policy;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void 무료_정책의_경우에는_금액_반환시_null을_반환한다() {
        FreeSessionPolicy freeSessionPolicy = new FreeSessionPolicy();

        assertThat(freeSessionPolicy.price()).isNull();
    }

    @Test
    void 무료_정책의_경우에는_정원_반환시_null을_반환한다() {
        FreeSessionPolicy freeSessionPolicy = new FreeSessionPolicy();

        assertThat(freeSessionPolicy.capacity()).isNull();
    }
}
