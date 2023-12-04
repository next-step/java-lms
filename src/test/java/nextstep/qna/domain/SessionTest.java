package nextstep.qna.domain;

import ch.qos.logback.core.util.FileSize;
import nextstep.qna.domain.session.SessionStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    public static final Session S1 = PaidSession.of("2023-12-04T00:00:00", "2024-01-04T00:00:00", CoverImage.of("dd", FileSize.valueOf("50kb"), "gif", new Pixel(300), new Pixel(200)), SessionStatus.END, 100, 0, 50000);
    public static final Session S2 = PaidSession.of("2023-12-03T00:00:00", "2024-01-03T00:00:00", CoverImage.of("dd", FileSize.valueOf("50kb"), "gif", new Pixel(300), new Pixel(200)), SessionStatus.RECRUITING, 50, 0, 100000);
    public static final Session S3 = PaidSession.of("2023-12-03T00:00:00", "2024-01-03T00:00:00", CoverImage.of("dd", FileSize.valueOf("50kb"), "gif", new Pixel(300), new Pixel(200)), SessionStatus.RECRUITING, 50, 50, 100000);
    public static final Session S4 = FreeSession.of("2023-12-24T00:00:00", "2004-01-24T00:00:00", CoverImage.of("dd", FileSize.valueOf("50kb"), "gif", new Pixel(300), new Pixel(200)), SessionStatus.READY);
    public static final Session S5 = FreeSession.of("2024-01-01T00:00:00", "2024-02-01T00:00:00", CoverImage.of("dd", FileSize.valueOf("50kb"), "gif", new Pixel(300), new Pixel(200)), SessionStatus.RECRUITING);

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
        assertThrowsExactly(IllegalStateException.class, () -> S1.checkForRegister(50000), "강의 상태가 모집중이 아닙니다.");
    }

    @Test
    void 유료강의_checkSessionStatus_실패_가격오류() {
        assertThrowsExactly(IllegalArgumentException.class, () -> S2.checkForRegister(50000), "강의 가격과 다릅니다.");
    }

    @Test
    void 유료강의_checkSessionStatus_실패_수강인원초과() {
        assertThrowsExactly(IllegalStateException.class, () -> S3.checkForRegister(100000), "수강인원이 초과 되었습니다.");
    }

    @Test
    void 유료강의_checkSessionStatus_성공() {
        assertTrue(S2.checkForRegister(100000));
    }

    @Test
    void 무료강의_checkSessionStatus_실패_상태오류() {
        assertThrowsExactly(IllegalStateException.class, () -> S4.checkForRegister(0), "강의 상태가 모집중이 아닙니다.");
    }

    @Test
    void 무료강의_checkSessionStatus_성공() {
        assertTrue(S5.checkForRegister(0));
    }
}
