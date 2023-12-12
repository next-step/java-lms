package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    @Test
    public void 강의_상태_모집중() {
        assertThat(SessionStatus.status(new Session(LocalDate.now(), LocalDate.now())))
                .isEqualTo(SessionStatus.ENROLLING);
    }

    @Test
    public void 강의_상태_준비중() {
        assertThat(SessionStatus.status(new Session(LocalDate.now().plusDays(1), LocalDate.now().plusDays(2))))
                .isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    public void 강의_상태_모집_종료() {
        assertThat(SessionStatus.status(new Session(LocalDate.now().minusDays(1), LocalDate.now().minusDays(1))))
                .isEqualTo(SessionStatus.CLOSED);
    }
}
