package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CohortTest {

    @Test
    @DisplayName("같은 상태를 갖는 두 기수는 동일한 기수이다.")
    void 기수_생성() {
        Assertions.assertThat(Cohort.of(1L)).isEqualTo(Cohort.of(1L));
    }

}