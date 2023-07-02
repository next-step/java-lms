package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProgressStateTest {

    @Test
    void 생성자테스트 () {
        Assertions.assertThat(ProgressState.END).isEqualTo(ProgressState.END);
    }

}
