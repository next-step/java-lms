package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecruitmentStateTest {

    @Test
    void 동치비교 () {
        Assertions.assertThat(RecruitmentState.RECRUITING).isEqualTo(RecruitmentState.RECRUITING);
    }

}
