package nextstep.session;

import nextstep.session.domain.EndAt;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.StartAt;
import nextstep.session.domain.Users;
import nextstep.users.domain.NsUserTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

class TestFixtures {

    static Session endSession() {
        return new Session(
                null,
                new Users(30, Set.of()),
                BigDecimal.valueOf(1000),
                SessionType.FREE,
                SessionStatus.END,
                null,
                new StartAt(LocalDateTime.now().plusDays(2)),
                new EndAt(LocalDateTime.now().plusDays(7))
        );
    }

    static Session preparingSession() {
        return new Session(
                null,
                new Users(30, Set.of()),
                BigDecimal.valueOf(1000),
                SessionType.FREE,
                SessionStatus.PREPARING,
                null,
                new StartAt(LocalDateTime.now().plusDays(2)),
                new EndAt(LocalDateTime.now().plusDays(7))
        );
    }

    static Session recrutingPaidSession() {
        return new Session(
                null,
                new Users(1, Set.of(NsUserTest.JAVAJIGI)),
                BigDecimal.valueOf(1000),
                SessionType.PAID,
                SessionStatus.RECRUITING,
                null,
                new StartAt(LocalDateTime.now().plusDays(2)),
                new EndAt(LocalDateTime.now().plusDays(7))
        );
    }

    static Session registableRecrutingPaidSession() {
        return new Session(
                null,
                new Users(999, Set.of(NsUserTest.JAVAJIGI)),
                BigDecimal.valueOf(1000),
                SessionType.PAID,
                SessionStatus.RECRUITING,
                null,
                new StartAt(LocalDateTime.now().plusDays(2)),
                new EndAt(LocalDateTime.now().plusDays(7))
        );
    }

    static Session registableRecrutingFreeSession() {
        return new Session(
                null,
                new Users(999, Set.of(NsUserTest.JAVAJIGI)),
                BigDecimal.valueOf(999),
                SessionType.FREE,
                SessionStatus.RECRUITING,
                null,
                new StartAt(LocalDateTime.now().plusDays(2)),
                new EndAt(LocalDateTime.now().plusDays(7))
        );
    }
}
