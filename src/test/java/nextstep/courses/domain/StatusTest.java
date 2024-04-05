package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.Status.*;

public class StatusTest {

    @Test
    void end() {
        Assertions.assertThat(END == END).isTrue();
    }

    @Test
    void recruiting() {
        Assertions.assertThat(RECRUITING == RECRUITING).isTrue();
    }

    @Test
    void preparing() {
        Assertions.assertThat(PREPARING == PREPARING).isTrue();
    }
}
