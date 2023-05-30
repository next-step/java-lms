package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    void 등록할수_없는_상태() {
        Session session1 = new Session(SessionStatus.PREPARING);
        Session session2 = new Session(SessionStatus.OPEN);
        Session session3 = new Session(SessionStatus.CLOSED);

        assertThat(session1.canRegisteringStatus()).isFalse();
        assertThat(session2.canRegisteringStatus()).isTrue();
        assertThat(session3.canRegisteringStatus()).isFalse();
    }

    @Test
    void 최대인원_초과_불과() {
        List<Registration> registrations = List.of(new Registration(), new Registration());
        Session session = new Session(0L, null, null, null, true, null, 1,null, null, null);

        assertThat(session.overMaxStudents(registrations.size())).isTrue();
    }
}