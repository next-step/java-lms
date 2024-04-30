package nextstep.sessions.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class SessionsTest {
    Sessions sessions;
    Session session;
    @BeforeEach
    void setUp() {
        session = Session.createFreeSession(0L, 0L, new SessionPeriod(LocalDate.of(2024, 04, 21), LocalDate.of(2024, 04, 21)), new SessionImage(1000, "hi.jpg", 300, 200), SessionStatus.PREPARING);
        sessions = new Sessions(List.of(session));

    }
    @Test
    @DisplayName("같은 강의가 동시에 존재할 수 없다.")
    void checkDuplicateSession() {
        Session newSession = Session.createFreeSession(0L, 0L, new SessionPeriod(LocalDate.of(2024, 04, 21), LocalDate.of(2024, 04, 21)), new SessionImage(1000, "hi.jpg", 300, 200), SessionStatus.PREPARING);
        assertThatIllegalArgumentException().isThrownBy(
                () -> sessions.add(newSession)
        ).withMessageContaining("이미 존재하는 강의는 추가할 수 없습니다.");

    }
}