package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.coverImage.ImageExtension;
import nextstep.courses.domain.session.coverImage.SessionCoverImage;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    private Session session;

    @Test
    void 커버_이미지_확인() {
        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.RECRUITING, (payment) -> true);
        assertTrue(session.isValidCoverImage());
    }

    @Test
    void 수강신청_가능여부_확인__인원초과_확인() {
        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.RECRUITING, new PaidSession(3, 2, 20000));
        assertTrue(session.canEnroll(new Payment(20000L)));
        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.RECRUITING, new PaidSession(2, 2, 20000));
        assertFalse(session.canEnroll(new Payment(20000L)));
    }

    @Test
    void 수강신청_가능여부_확인__강의_수강료_확인() {
        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.RECRUITING, new PaidSession(3, 2, 20000));
        assertTrue(session.canEnroll(new Payment(20000L)));
        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.RECRUITING, new PaidSession(3, 2, 20000));
        assertFalse(session.canEnroll(new Payment(15000L)));
    }

    @Test
    void 수강신청_가능여부_확인__강의_상태_모집중() {
        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.RECRUITING, (payment) -> true);
        assertTrue(session.canEnroll(new Payment(20000L)));

        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.CLOSED, new FreeSession());
        assertFalse(session.canEnroll(new Payment(0L)));

        session = new Session(new SessionCoverImage(1, ImageExtension.GIF, 300, 200), SessionStatus.CLOSED, new PaidSession(3, 2, 20000));
        assertFalse(session.canEnroll(new Payment(20000L)));
    }

}
