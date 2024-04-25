package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionTest {

    Image image;

    @BeforeEach
    void setUp() {
        image = new Image("test.png", 300, 200, 1_000);
    }

    @Test
    void 무료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.FREE, SessionState.RECRUITING, image, now.plusDays(5), now.plusDays(30));
        session.register(NsUserTest.JAVAJIGI);
    }

    @Test
    void 무료강의_신청_모집중_이외에는_예외발생() {
        LocalDateTime now = LocalDateTime.now();
        Session session1 = new Session(1L, "lms", SessionType.FREE, SessionState.READY, image, now.plusDays(5), now.plusDays(30));
        assertThatThrownBy(() -> session1.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);

        Session session2 = new Session(1L, "lms", SessionType.FREE, SessionState.END, image, now.plusDays(5), now.plusDays(30));
        assertThatThrownBy(() -> session2.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    void 유료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.PAID, SessionState.RECRUITING, image, now.plusDays(5), now.plusDays(30), 10, 5_000);
        Payment payment = new Payment("1", 1L, NsUserTest.JAVAJIGI.getId(), 5_000L);
        session.register(NsUserTest.JAVAJIGI, payment);
    }
}
