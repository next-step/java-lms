package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @Test
    @DisplayName("오늘 날짜에 맞는 준비중, 진행중, 종료를 반환한다.")
    void create() {
        SessionStatus preparing = SessionStatus.of(LocalDate.now(), LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 31));
        assertThat(preparing).isEqualTo(SessionStatus.PREPARING);

        SessionStatus inProgress = SessionStatus.of(LocalDate.now(), LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31));
        assertThat(inProgress).isEqualTo(SessionStatus.IN_PROGRESS);

        SessionStatus completed = SessionStatus.of(LocalDate.now(), LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 5));
        assertThat(completed).isEqualTo(SessionStatus.COMPLETED);
    }
}
