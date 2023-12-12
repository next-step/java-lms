package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    public static final Session ENROLLING_SESSION = new Session(LocalDate.now(), LocalDate.now());
    public static final Session PREPARING_SESSION = new Session(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2));
    public static final Session CLOSED_SESSION = new Session(LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));

    @Test
    public void 강의_상태_모집중() {
        assertThat(SessionStatus.status(ENROLLING_SESSION))
                .isEqualTo(SessionStatus.ENROLLING);
    }

    @Test
    public void 강의_상태_준비중() {
        assertThat(SessionStatus.status(PREPARING_SESSION))
                .isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    public void 강의_상태_모집_종료() {
        assertThat(SessionStatus.status(CLOSED_SESSION))
                .isEqualTo(SessionStatus.CLOSED);
    }
}
