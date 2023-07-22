package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static nextstep.courses.domain.SessionPeriodTest.SESSION_PERIOD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    static final Session SESSION01;

    static {
        SESSION01 = Session.createSession(new Image(""), PaymentType.FREE, SessionState.PREPARING, 30, SESSION_PERIOD);
    }

    @Test
    public void create() throws Exception {
        Image coverImage = new Image("");
        assertThat(
                Session.createSession(coverImage, PaymentType.FREE, SessionState.PREPARING, 30, SESSION_PERIOD)
        ).isNotNull()
                .isInstanceOf(Session.class);
    }

    @Test
    public void 수강인원초과() throws Exception {
        Session session = Session.createFreeSession(1, SESSION_PERIOD);
        final List<NsUser> newStudents = Arrays.asList(new NsUser(), new NsUser());

        assertThatThrownBy(() -> {
            session.registerSessionAll(newStudents);
        }).isInstanceOf(CannotRegisterSessionException.class);
    }

    @Test
    public void 세션모집완료() throws Exception {
        Session session = Session.createSession(new Image(""), PaymentType.CHARGED, SessionState.CLOSED, 1, SESSION_PERIOD);
        NsUser student1 = new NsUser();

        assertThatThrownBy(() -> {
            session.registerSession(student1);
        }).isInstanceOf(CannotRegisterSessionException.class);
    }
}
