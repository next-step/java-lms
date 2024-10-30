package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    private Session session;

    @Test
    void 커버_이미지_확인() {
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.RECRUITING, () -> true);
        assertTrue(session.isValidCoverImage());
    }

    @Test
    void 수강신청_가능여부_확인__인원초과_확인() {
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.RECRUITING, new PaidSession(3, 2, 20000, 20000));
        assertTrue(session.canEnroll());
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.RECRUITING, new PaidSession(2, 2, 20000, 20000));
        assertFalse(session.canEnroll());
    }

    @Test
    void 수강신청_가능여부_확인__강의_수강료_확인() {
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.RECRUITING, new PaidSession(3, 2, 20000, 20000));
        assertTrue(session.canEnroll());
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.RECRUITING, new PaidSession(3, 2, 20000, 15000));
        assertFalse(session.canEnroll());
    }

    @Test
    void 수강신청_가능여부_확인__강의_상태_모집중() {
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.RECRUITING, () -> true);
        assertTrue(session.canEnroll());
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.CLOSED, new FreeSession());
        assertFalse(session.canEnroll());
        session = new Session(new SessionCoverImage("gif", 300, 200), SessionStatus.CLOSED, new PaidSession(3, 2, 20000, 20000));
        assertFalse(session.canEnroll());
    }

}
