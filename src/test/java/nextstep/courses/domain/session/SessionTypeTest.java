package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTypeTest {

    @Test
    public void 무료강의_생성() {
        SessionType sessionType = new FreeSessionType();

        assertThat(sessionType).isNotNull();
        assertThat(sessionType.isFree()).isTrue();
    }

    @Test
    public void 유료강의_생성() {
        int maxCapacity = 10;
        long fee = 100_000L;

        PaidSessionType sessionType = new PaidSessionType(maxCapacity, fee);

        assertThat(sessionType).isNotNull();
        assertThat(sessionType.isFree()).isFalse();
        assertThat(sessionType.getMaxCapacity()).isEqualTo(maxCapacity);
        assertThat(sessionType.getFee()).isEqualTo(fee);
    }

    @Test
    public void 유료강의_인원_제한_여부_확인() {
        SessionType sessionType = new PaidSessionType(2, 100_000L);

        assertThat(sessionType.isOverCapacity(1)).isFalse();
        assertThat(sessionType.isOverCapacity(2)).isTrue();
        assertThat(sessionType.isOverCapacity(3)).isTrue();
    }

    @Test
    public void 무료강의는_인원_제한이_없음() {
        SessionType sessionType = new FreeSessionType();

        assertThat(sessionType.isOverCapacity(100)).isFalse();
        assertThat(sessionType.isOverCapacity(1000)).isFalse();
    }

    @Test
    public void 유료강의_결제금액_검증() {
        SessionType sessionType = new PaidSessionType(10, 100_000L);

        assertThat(sessionType.isValidPayment(100_000L)).isTrue();
        assertThat(sessionType.isValidPayment(50_000L)).isFalse();
        assertThat(sessionType.isValidPayment(null)).isFalse();
    }

    @Test
    public void 무료강의는_결제금액_검증_불필요() {
        SessionType sessionType = new FreeSessionType();

        assertThat(sessionType.isValidPayment(null)).isTrue();
        assertThat(sessionType.isValidPayment(0L)).isTrue();
    }
}
