package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionPeriodTest {
    
    @DisplayName("강의 기간 생성")
    @Test
    void 강의기간생성() {
        // given
        LocalDateTime startDateTime = LocalDateTime.of(2021, 8, 1, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2021, 8, 31, 23, 59);
        SessionPeriod sessionPeriod = new SessionPeriod(startDateTime, endDateTime);
        // when
        // then
        assertThat(sessionPeriod.startDate()).isEqualTo(startDateTime);
    }
}
