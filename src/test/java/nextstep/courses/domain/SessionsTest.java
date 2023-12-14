package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionsTest {
    @Test
    @DisplayName("Sessions 생성")
    void create() {
        assertThat(new Sessions()).isInstanceOf(Sessions.class);
    }
}
