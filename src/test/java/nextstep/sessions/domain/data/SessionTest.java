package nextstep.sessions.domain.data;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.registrations.domain.data.Registration;
import nextstep.registrations.domain.data.Registrations;
import nextstep.sessions.domain.data.type.PayType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.SessionType;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {


    @Test
    void 유료_강의_최대_인원_초과() {
        Session s = new Session(new SessionType(PayType.PAY, 800000, 2), SessionState.RECRUITING);
        Registrations registrations = new Registrations(List.of(
            new Registration(s, NsUserTest.JAVAJIGI, new Payment()),
            new Registration(s, NsUserTest.SANJIGI, new Payment())
        ));
        Session session = s.with(registrations);

        assertThatThrownBy(session::validateEnrollment)
            .isInstanceOf(SessionsException.class)
            .hasMessage("강의 최대 인원을 초과했습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = SessionState.class, names = "RECRUITING", mode = EnumSource.Mode.EXCLUDE)
    void 강의_모집중_아님(SessionState sessionState) {
        Session s = new Session(new SessionType(PayType.PAY, 800000, 2), sessionState);
        Registrations registrations = new Registrations(List.of(
            new Registration(s, NsUserTest.JAVAJIGI, new Payment()),
            new Registration(s, NsUserTest.SANJIGI, new Payment())
        ));
        Session session = s.with(registrations);

        assertThatThrownBy(session::validateEnrollment)
            .isInstanceOf(SessionsException.class)
            .hasMessage("모집중이 아닌 강의입니다.");
    }
}
