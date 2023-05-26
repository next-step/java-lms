package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GenerationTest {

    @Test
    @DisplayName("기수는 코스와 기수 수를 가진다.")
    void newInstance() {
        Generation actual = new Generation(1L, 0L);
        assertThat(actual).isEqualTo(new Generation(1L, 0L));
    }
}
