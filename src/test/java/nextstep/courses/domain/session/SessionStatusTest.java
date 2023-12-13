package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    public static final Schedule ENROLLING_SCHEDULE = new Schedule();
    public static final Schedule PREPARING_SCHEDULE = new Schedule(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
    public static final Schedule CLOSED_SCHEDULE = new Schedule(LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));

    @Test
    public void 강의_상태_모집중() {
        assertThat(SessionStatus.status(ENROLLING_SCHEDULE))
                .isEqualTo(SessionStatus.ENROLLING);
    }

    @Test
    public void 강의_상태_준비중() {
        assertThat(SessionStatus.status(PREPARING_SCHEDULE))
                .isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    public void 강의_상태_모집_종료() {
        assertThat(SessionStatus.status(CLOSED_SCHEDULE))
                .isEqualTo(SessionStatus.CLOSED);
    }
}
