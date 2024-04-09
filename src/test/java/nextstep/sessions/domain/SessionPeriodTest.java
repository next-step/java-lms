package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class SessionPeriodTest {

    @Test
    void 종료일이_시작일보다_빠르면_예외가_발생한다() {
        final LocalDateTime startedAT = LocalDateTime.of(2024, 03, 02, 0, 0, 0);
        final LocalDateTime endAt = LocalDateTime.of(2024, 03, 01, 0, 0, 0);

        assertThatIllegalArgumentException().isThrownBy(() -> new SessionPeriod(startedAT, endAt))
                .withMessage("종료일이 시작일보다 빠를 수 없습니다.");
    }

    @Test
    void 시작일이_지난_강의를_수강_신청을_하면_실패한다() {
        final SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2));

        assertThatIllegalArgumentException().isThrownBy(sessionPeriod::validateStartedAt)
                .withMessage("시작일이 지난 강의는 수강 신청할 수 없습니다.");
    }
}
