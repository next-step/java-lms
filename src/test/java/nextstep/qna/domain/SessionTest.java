package nextstep.qna.domain;

import nextstep.payments.domain.Payment;
import nextstep.qna.domain.session.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    public static final Session S1 = PaidSession.of("2023-12-04T00:00:00", "2024-01-04T00:00:00", CoverImage.of("dd", 1024*50, "gif", Pixel.of(300), Pixel.of(200)), SessionStatus.END, 100, 0, 50000);
    public static final Session S2 = PaidSession.of("2023-12-03T00:00:00", "2024-01-03T00:00:00", CoverImage.of("dd", 1024*50, "gif", Pixel.of(300), Pixel.of(200)), SessionStatus.RECRUITING, 50, 0, 100000);
    public static final Session S3 = PaidSession.of("2023-12-03T00:00:00", "2024-01-03T00:00:00", CoverImage.of("dd", 1024*50, "gif", Pixel.of(300), Pixel.of(200)), SessionStatus.RECRUITING, 50, 50, 100000);
    public static final Session S4 = FreeSession.of("2023-12-24T00:00:00", "2004-01-24T00:00:00", CoverImage.of("dd", 1024*50, "gif", Pixel.of(300), Pixel.of(200)), SessionStatus.READY);
    public static final Session S5 = FreeSession.of("2024-01-01T00:00:00", "2024-02-01T00:00:00", CoverImage.of("dd", 1024*50, "gif", Pixel.of(300), Pixel.of(200)), SessionStatus.RECRUITING);

    @Test
    void isFreeSession() {
        assertTrue(S1 instanceof PaidSession);
    }

    @Test
    void isPaidSession() {
        assertTrue(S4 instanceof FreeSession);
    }

    @Test
    void 유료강의_checkSessionStatus_실패_상태오류() {
        Payment panyment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 50000L);
        assertThrowsExactly(IllegalStateException.class, () -> S1.isPossibleToRegister(panyment), "강의 상태가 모집중이 아닙니다.");
    }

    @Test
    void 유료강의_checkSessionStatus_실패_가격오류() {
        Payment panyment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 50000L);
        assertThrowsExactly(IllegalArgumentException.class, () -> S2.isPossibleToRegister(panyment), "강의 가격과 다릅니다.");
    }

    @Test
    void 유료강의_checkSessionStatus_실패_수강인원초과() {
        Payment panyment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 100000L);
        assertThrowsExactly(IllegalStateException.class, () -> S3.isPossibleToRegister(panyment), "수강인원이 초과 되었습니다.");
    }

    @Test
    void 유료강의_checkSessionStatus_성공() {
        Payment panyment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 100000L);
        assertTrue(S2.isPossibleToRegister(panyment));
    }

    @Test
    void 무료강의_checkSessionStatus_실패_상태오류() {
        Payment panyment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 0L);
        assertThrowsExactly(IllegalStateException.class, () -> S4.isPossibleToRegister(panyment), "강의 상태가 모집중이 아닙니다.");
    }

    @Test
    void 무료강의_checkSessionStatus_성공() {
        Payment panyment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 0L);
        assertTrue(S5.isPossibleToRegister(panyment));
    }
}
