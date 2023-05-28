package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    static final Session SESSION01;

    static {
        Image coverImage = new Image("");
        LocalDate startDate = LocalDate.of(2023, 05, 27);
        LocalDate endDate = LocalDate.of(2023, 06, 25);
        SESSION01 = new Session(coverImage, PaymentType.FREE, SessionState.PREPARING, 30, startDate, endDate);
    }

    @Test
    public void create() throws Exception {
        Image coverImage = new Image("");
        LocalDate startDate = LocalDate.of(2023, 05, 27);
        LocalDate endDate = LocalDate.of(2023, 06, 25);
        assertThat(
                new Session(coverImage, PaymentType.FREE, SessionState.PREPARING, 30, startDate, endDate)
        ).isNotNull()
                .isInstanceOf(Session.class);
    }

    @Test
    public void 수강인원초과() throws Exception {
        LocalDate startDate = LocalDate.of(2023, 05, 27);
        LocalDate endDate = LocalDate.of(2023, 06, 25);
        Session session = new Session(1, startDate, endDate);
        NsUser student1 = new NsUser();
        NsUser student2 = new NsUser();

        assertThatThrownBy(() -> {
            session.registerSession(Arrays.asList(student1, student2));
        }).isInstanceOf(CannotRegisterSessionException.class);
    }

    @Test
    public void 세션모집완료() throws Exception {
        LocalDate startDate = LocalDate.of(2023, 05, 27);
        LocalDate endDate = LocalDate.of(2023, 06, 25);
        Session session = new Session(1, startDate, endDate);
        NsUser student1 = new NsUser();

        session.close();
        assertThatThrownBy(() -> {
            session.registerSession(student1);
        }).isInstanceOf(CannotRegisterSessionException.class);
    }
}
