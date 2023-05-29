package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StatusTest {

    @Test
    @DisplayName("find_method_test")
    public void find_method_test() {
        assertThat(Status.find("READY")).isEqualTo(Status.READY);
        assertThat(Status.find("test")).isNull();
    }
}
