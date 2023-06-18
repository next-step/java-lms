package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseDurationTest {

    @Test
    void checkCourseDuration() {
        assertThatThrownBy(()->new SessionDuration(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("시작일과 종료일은 모두 선택해 줘야 됩니다.");
    }

    @Test
    void validateDateRange() {
        assertThatThrownBy(()->new SessionDuration(LocalDateTime.of(2023, 7, 2,0 ,0) , LocalDateTime.of(2023, 7, 1,0 ,0) ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료일이 시작일보다 이전입니다.");
    }

    @Test
    void validateStartDate() {
        assertThatThrownBy(()->new SessionDuration(LocalDateTime.of(2023, 6, 3,0 ,0) , LocalDateTime.of(2023, 6, 12,0 ,0) ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 현재시간의 최소 7일 이후에 등록해야됩니다.");
    }
}