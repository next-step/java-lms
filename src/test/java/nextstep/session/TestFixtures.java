package nextstep.session;

import nextstep.session.domain.EndAt;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.StartAt;

import java.time.LocalDateTime;
import java.util.Set;

class TestFixtures {

    static Session endSession() {
        return new Session(
                null,
                Set.of(),
                30,
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
                Set.of(),
                30,
                SessionType.FREE,
                SessionStatus.END,
                null,
                new StartAt(LocalDateTime.now().plusDays(2)),
                new EndAt(LocalDateTime.now().plusDays(7))
        );
    }
}
