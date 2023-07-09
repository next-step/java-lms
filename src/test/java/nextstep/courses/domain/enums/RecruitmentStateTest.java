package nextstep.courses.domain.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RecruitmentStateTest {

    @Test
    void 동일비교 () {
        assertThat(RecruitmentState.RECRUITING).isEqualTo(RecruitmentState.RECRUITING);
    }

    @Test
    void 동치비교 () {
        assertThat(RecruitmentState.of(1)).isEqualTo(RecruitmentState.RECRUITING);
    }

    @Test
    void 미존재값조회() {
        assertThatThrownBy(() -> {
            RecruitmentState recruitmentState = RecruitmentState.of(10);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid RecruitmentState value: 10");
    }
}
