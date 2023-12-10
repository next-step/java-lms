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
        coverImage = new CoverImage(300, 200, 3000L, ImageType.SVG);
        freeSession = new Session(1L, "name", sessionPeriod, SessionState.FINISHED, coverImage, SessionFeeType.FREE, null, new EnrollmentCount(1), LocalDateTime.now(), null);
    }

    @Test
    void validateSessionStateTest() {
        assertThatThrownBy(() -> {
            freeSession.validateEnrollState(new Payment("id", 1L, JAVAJIGI.getId(), 0L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkEnrollmentAvailableTest_isNoRemaining() {
        paidSession = new Session(1L, "name", sessionPeriod, SessionState.RECRUITING, coverImage, SessionFeeType.PAID, new SessionFee(1000L), new EnrollmentCount(0), LocalDateTime.now(), null);
        assertThatThrownBy(() -> {
            paidSession.validateEnrollState(new Payment("id", 1L, JAVAJIGI.getId(), 1000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkEnrollmentAvailableTest_isNotEqualToFee() {
        paidSession = new Session(1L, "name", sessionPeriod, SessionState.RECRUITING, coverImage, SessionFeeType.PAID, new SessionFee(1000L), new EnrollmentCount(1), LocalDateTime.now(), null);
        assertThatThrownBy(() -> {
            paidSession.validateEnrollState(new Payment("id", 1L, JAVAJIGI.getId(), 2000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
