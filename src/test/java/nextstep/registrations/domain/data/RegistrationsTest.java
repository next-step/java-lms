package nextstep.registrations.domain.data;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.registrations.domain.exception.RegistrationException;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegistrationsTest {


    @Test
    void 강의_최대_인원_초과() {
        Session session = Session.ofPaidSession(800000, 1, SessionState.RECRUITING);
        Registrations registrations = new Registrations(List.of(
            new Registration(session, NsUserTest.JAVAJIGI, new Payment()),
            new Registration(session, NsUserTest.SANJIGI, new Payment())
        ));

        assertThatThrownBy(registrations::validateSession)
            .isInstanceOf(RegistrationException.class)
            .hasMessage("강의 최대 인원을 초과했습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = SessionState.class, names = "RECRUITING", mode = EnumSource.Mode.EXCLUDE)
    void 강의_모집중_아님(SessionState sessionState) {
        Session session = Session.ofPaidSession(800000, 3, sessionState);
        Registrations registrations = new Registrations(List.of(
            new Registration(session, NsUserTest.JAVAJIGI, new Payment()),
            new Registration(session, NsUserTest.SANJIGI, new Payment())
        ));

        assertThatThrownBy(registrations::validateSession)
            .isInstanceOf(RegistrationException.class)
            .hasMessage("모집중이 아닌 강의입니다.");
    }
}
