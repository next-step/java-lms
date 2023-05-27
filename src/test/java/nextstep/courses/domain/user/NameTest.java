package nextstep.courses.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("동일한 상태를 갖는 두 이름은 동일한 이름이다.")
    void 이름_생성() {
        assertThat(Name.of(1L,"이름")).isEqualTo(Name.of(1L,"이름"));
    }

}