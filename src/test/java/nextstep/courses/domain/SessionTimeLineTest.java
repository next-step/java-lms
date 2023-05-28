package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTimeLineTest {
    @DisplayName("강의 시작일이 강의 종료일 보다 빠를 경우 예외를 발생하지 않는다.")
    @Test
    void when_CreatedTimeIsEarlierThanClosedTime_Expects_DoesNotThrowException() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime closedAt = createdAt.plusDays(10);

        assertThatNoException()
                .isThrownBy(() -> new SessionTimeLine(createdAt, closedAt));
    }

    @DisplayName("강의 종료일이 강의 시작일 보다 빠른 경우 예외를 발생한다.")
    @Test
    void when_CreatedTimeIsLaterThanClosedTime_Expects_ThrowException() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime closedAt = createdAt.minusDays(10);

        assertThatThrownBy(() -> new SessionTimeLine(createdAt, closedAt))
                .isInstanceOf(IllegalArgumentException.class);
    }
}