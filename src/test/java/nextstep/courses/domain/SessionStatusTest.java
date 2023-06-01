package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionStatusTest {

    @Test
    @DisplayName("find_method_test")
    public void find_method_test() {
        assertThat(SessionStatus.find("READY")).isEqualTo(SessionStatus.READY);
        assertThat(SessionStatus.find("test")).isNull();
    }
}
