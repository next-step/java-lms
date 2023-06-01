package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SessionStateTest {

    @Test
    void 생성자테스트 () {
        Assertions.assertThat(SessionState.END).isEqualTo(SessionState.END);
    }

}
