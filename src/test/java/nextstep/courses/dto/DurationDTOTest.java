package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DurationDTOTest {
    @Test
    @DisplayName("DurationDTO 생성")
    void create() {
        assertThat(new DurationDTO(
                LocalDateTime.of(2024,1,1,0,0),
                LocalDateTime.of(2024,2,1,0,0)
            )
        ).isInstanceOf(DurationDTO.class);
    }

    @Test
    @DisplayName("기간 중 시작날짜 반환")
    void getStartDate() {
        assertThat(new DurationDTO(
                LocalDateTime.of(2024,1,1,0,0),
                LocalDateTime.of(2024,2,1,0,0)
            ).getStartDate()
        ).isEqualTo(LocalDateTime.of(2024,1,1,0,0));
    }

    @Test
    @DisplayName("기간 중 종료날짜 반환")
    void getEndDate() {
        assertThat(new DurationDTO(
                LocalDateTime.of(2024,1,1,0,0),
                LocalDateTime.of(2024,2,1,0,0)
            ).getEndDate()
        ).isEqualTo(LocalDateTime.of(2024,2,1,0,0));
    }
}
