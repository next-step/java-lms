package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NsUserLimitDTOTest {
    @Test
    @DisplayName("NsUserLimitDTO 생성")
    void create() {
        assertThat(new NsUserLimitDTO(10)).isInstanceOf(NsUserLimitDTO.class);
    }

    @Test
    @DisplayName("리미트 값 반환")
    void getLimit() {
        assertThat(new NsUserLimitDTO(10).getLimit()).isEqualTo(10);
    }
}
