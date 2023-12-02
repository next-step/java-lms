package nextstep.sessions.domain.data.vo;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationsTest {

    @Test
    void size() {
        SessionInfo sessionInfo = new SessionInfo(new SessionType(PaidType.PAID, 800000, 2), SessionState.RECRUITING);
        Registrations registrations = new Registrations(List.of(
            new Registration(new Session(sessionInfo), new Payment()),
            new Registration(new Session(sessionInfo), new Payment())
        ));

        assertThat(registrations.size()).isEqualTo(2);
    }
}
