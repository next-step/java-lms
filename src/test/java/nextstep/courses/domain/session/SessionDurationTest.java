package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionDurationTest {

    @Test
    @DisplayName("같은 상태를 갖는 두 강의기간은 동일한 강의기간이다.")
    void 강의기간_생성() {
        LocalDateTime startDate = LocalDateTime.parse("2023-05-01T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-31T23:59:59");
        assertThat(SessionDuration.of(1L, startDate, endDate)).isEqualTo(SessionDuration.of(1L, startDate, endDate));
    }

}