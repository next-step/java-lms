package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionPeriodTest {

    @DisplayName("(강의)시작일이 종료일보다 큰 경우")
    @Test
    void 시작일이_종료일보다_큰_경우() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.minusDays(1);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionPeriod(startedAt, endedAt))
                .withMessageMatching("시작일은 종료일 이전이어야 합니다.");
    }

    @DisplayName("강의기간(SessionPeriod) 객체 생성 테스트")
    @Test
    void SessionPeriod생성() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endedAt = startedAt.plusDays(1);

        SessionPeriod sessionPeriod = new SessionPeriod(startedAt, endedAt);
        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(startedAt, endedAt));
    }
}
