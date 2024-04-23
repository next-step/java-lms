package nextstep.courses.domain;

import nextstep.courses.domain.tuition.Money;
import nextstep.courses.domain.tuition.Tuition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TuitionTest {

    @Test
    void need_to_pay() {
        assertThat(new Tuition(false, 20000).isEnough(new Money(20000))).isTrue();
    }
}
