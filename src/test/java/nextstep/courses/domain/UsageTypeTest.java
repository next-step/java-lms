package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.UsageType.FREE;

public class UsageTypeTest {

    @Test
    void PAY() {
        Assertions.assertThat(FREE == FREE).isTrue();
    }

    @Test
    void FREE() {
        Assertions.assertThat(FREE == FREE).isTrue();
    }
}
