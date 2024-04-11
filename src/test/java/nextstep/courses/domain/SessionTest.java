package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionTest {

    @Test
    void 무료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.FREE, SessionState.RECRUITING, "test.jpg", now.plusDays(5), now.plusDays(30));
        session.register(NsUserTest.JAVAJIGI);
    }

    @Test
    void 무료강의_신청_모집중_이외에는_예외발생() {
        LocalDateTime now = LocalDateTime.now();
        Session session1 = new Session(1L, "lms", SessionType.FREE, SessionState.READY, "test.jpg", now.plusDays(5), now.plusDays(30));
        assertThatThrownBy(() -> session1.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);

        Session session2 = new Session(1L, "lms", SessionType.FREE, SessionState.END, "test.jpg", now.plusDays(5), now.plusDays(30));
        assertThatThrownBy(() -> session2.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    void 유료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.PAID, SessionState.RECRUITING, "test.jpg", now.plusDays(5), now.plusDays(30), 10, 5_000);
        Payment payment = new Payment("1", 1L, NsUserTest.JAVAJIGI.getId(), 5_000L);
        session.register(NsUserTest.JAVAJIGI, payment);
    }

    @Test
    void 유로강의_신청인원보다_더_많이_신청시_예외발생() {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.PAID, SessionState.RECRUITING, "test.jpg", now.plusDays(5), now.plusDays(30), 2, 5_000);

        assertThatThrownBy(() -> {
            session.register(NsUserTest.JAVAJIGI);
            session.register(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotRegisterException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {1_000L, 4_999L, 5_001L, 6_000L})
    void 유료강의_결제_금액이_다르면_예외발생(long amount) {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.PAID, SessionState.RECRUITING,
                "test.jpg", now.plusDays(5), now.plusDays(30), 2, 5_000);
        Payment payment = new Payment("1", 1L, NsUserTest.JAVAJIGI.getId(), amount);

        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI, payment))
                .isInstanceOf(CannotRegisterException.class);
    }
}
