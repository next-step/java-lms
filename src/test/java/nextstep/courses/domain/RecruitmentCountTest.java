package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RecruitmentCountTest {

    @DisplayName("강의모집인원이 음수일경우 에러를 던진다.")
    @Test
    void 강의_모집인원_음수_에러() {
        assertThatThrownBy(() -> {
            new RecruitmentCount(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}