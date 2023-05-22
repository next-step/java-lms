package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionPeriodTest {
    @Test
    void 강의_기간_생성() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1L);

        SessionPeriod dut = new SessionPeriod(startDate, endDate);
        assertThat(dut).isEqualTo(new SessionPeriod(startDate, endDate));
    }

    @Test
    void 강의_시작일이_종료일보다_늦는_경우() {
        LocalDate startDate = LocalDate.now().plusDays(1L);
        LocalDate endDate = LocalDate.now();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionPeriod(startDate, endDate));
    }
}
