package nextstep.courses.domain;

import nextstep.courses.domain.builder.SessionBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    void 등록할수_없는_상태() {
        SessionBuilder sessionBuilder = new SessionBuilder();
        Session session1 = sessionBuilder.sessionRegistrationStatus(new SessionRegistrationStatus(SessionStatus.PREPARING)).build();
        Session session2 = sessionBuilder.sessionRegistrationStatus(new SessionRegistrationStatus(SessionStatus.OPEN)).build();
        Session session3 = sessionBuilder.sessionRegistrationStatus(new SessionRegistrationStatus(SessionStatus.CLOSED)).build();

        assertThat(session1.canRegisteringStatus()).isFalse();
        assertThat(session2.canRegisteringStatus()).isTrue();
        assertThat(session3.canRegisteringStatus()).isFalse();
    }

    @Test
    void 최대인원_초과_불과() {
        List<Registration> registrations = List.of(new Registration(), new Registration());
        SessionBuilder sessionBuilder = new SessionBuilder();
        Session session = sessionBuilder.sessionRegistrationStatus(new SessionRegistrationStatus(1)).build();
        assertThat(session.overMaxStudents(registrations.size())).isTrue();
    }
}