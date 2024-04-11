package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SessionScheduleTest {

    @Test
    @DisplayName("강의 시작일이 종료일보다 크면 에러가 난다.")
    void createSessionEnrollment() {
        LocalDateTime startAt = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class,
                () -> new SessionSchedule(LocalDateTime.now(), startAt.minusDays(1)));
    }

}
