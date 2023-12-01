package nextstep.registrations.domain.data;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PayType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.SessionType;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationsTest {

    @Test
    void size() {
        Session session = new Session(new SessionType(PayType.PAY, 800000, 2), SessionState.RECRUITING);
        Registrations registrations = new Registrations(List.of(
            new Registration(session, NsUserTest.JAVAJIGI, new Payment()),
            new Registration(session, NsUserTest.SANJIGI, new Payment())
        ));

        assertThat(registrations.size()).isEqualTo(2);
    }
}
