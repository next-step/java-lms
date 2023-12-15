package nextstep.session;

import nextstep.common.BaseTimeEntity;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.Users;
import nextstep.users.domain.NsUserTest;

import java.time.LocalDateTime;
import java.util.Set;

public class TestFixtures {

    public static Session endSession() {
        return new Session(
                1L,
                new Users(30, Set.of()),
                1000L,
                SessionType.FREE,
                SessionStatus.END,
                null,
                new BaseTimeEntity(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(7))
        );
    }

    public static Session preparingSession() {
        return new Session(
                2L,
                new Users(30, Set.of()),
                1000L,
                SessionType.FREE,
                SessionStatus.PREPARING,
                null,
                new BaseTimeEntity(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(7))
        );
    }

    public static Session registableRecrutingPaidSession() {
        return new Session(
                null,
                new Users(999, Set.of(NsUserTest.JAVAJIGI)),
                1000L,
                SessionType.PAID,
                SessionStatus.RECRUITING,
                null,
                new BaseTimeEntity(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(7))
        );
    }

    public static Session registableRecrutingFreeSession() {
        return new Session(
                null,
                new Users(999, Set.of(NsUserTest.JAVAJIGI)),
                0L,
                SessionType.FREE,
                SessionStatus.RECRUITING,
                null,
                new BaseTimeEntity(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(7))
        );
    }
}
