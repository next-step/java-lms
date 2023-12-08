package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionTest {
    private Session paidSession;
    private Session freeSession;
    private SessionPeriod sessionPeriod;
    private CoverImage coverImage;

    @BeforeEach
    void setUp() {
        sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        coverImage = new CoverImage(3000L, 300, 200, ImageType.SVG);
        freeSession = new FreeSession(1L, "name", sessionPeriod, SessionState.FINISHED, null);
    }

    @Test
    void validateSessionStateTest() {
        assertThatThrownBy(() -> {
            freeSession.enroll(new Payment("id", 1L, JAVAJIGI.getId(), 0L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkEnrollmentAvailableTest_isNoRemaining() {
        paidSession = new PaidSession(1L, "name", sessionPeriod, SessionState.RECRUITING, coverImage, new SessionFee(1000L), 0);
        assertThatThrownBy(() -> {
            paidSession.enroll(new Payment("id", 1L, JAVAJIGI.getId(), 1000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkEnrollmentAvailableTest_isNotEqualToFee() {
        paidSession = new PaidSession(1L, "name", sessionPeriod, SessionState.RECRUITING, coverImage, new SessionFee(1000L), 1);
        assertThatThrownBy(() -> {
            paidSession.enroll(new Payment("id", 1L, JAVAJIGI.getId(), 2000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
