package nextstep.sessions.domain.data.vo;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegistrationsTest {

    @Test
    void 단순_size_메서드_테스트() {
        SessionInfo sessionInfo = new SessionInfo(new SessionType(PaidType.PAID, 800000, 2), SessionState.RECRUITING);
        Registrations registrations = new Registrations(List.of(
            new Registration(session(sessionInfo), NsUserTest.JAVAJIGI, new Payment()),
            new Registration(session(sessionInfo), NsUserTest.SANJIGI, new Payment())
        ));

        assertThat(registrations.size()).isEqualTo(2);
    }

    @Test
    void 이미_수강_신청된_유저() {
        SessionInfo sessionInfo = new SessionInfo(new SessionType(PaidType.PAID, 800000, 2), SessionState.RECRUITING);
        Registrations registrations = new Registrations(List.of(
            new Registration(session(sessionInfo), NsUserTest.JAVAJIGI, new Payment()),
            new Registration(session(sessionInfo), NsUserTest.SANJIGI, new Payment())
        ));

        assertThatThrownBy(() -> registrations.validateDuplicateEnrollment(NsUserTest.JAVAJIGI))
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미 수강신청된 사용자 입니다.");
    }

    private Session session(SessionInfo sessionInfo) {
        return new Session(sessionInfo, null);
    }
}
