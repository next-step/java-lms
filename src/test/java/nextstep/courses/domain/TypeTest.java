package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeTest {

    @Test
    @DisplayName("find_method_test")
    public void find_method_test(){
        assertThat(Type.find("FREE")).isEqualTo(Type.FREE);
        assertThat(Type.find("test")).isNull();
    }
}
