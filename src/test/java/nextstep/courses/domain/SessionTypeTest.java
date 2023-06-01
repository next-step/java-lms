package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTypeTest {

    @Test
    @DisplayName("find_method_test")
    public void find_method_test(){
        assertThat(SessionType.find("FREE")).isEqualTo(SessionType.FREE);
        assertThat(SessionType.find("test")).isNull();
    }
}
