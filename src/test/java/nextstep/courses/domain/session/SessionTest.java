package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionTest {

    private final CoverImage COVER_IMAGE = new CoverImage(1, "png", 300, 200);
    private final LocalDateTime START_DATE = LocalDateTime.of(2025, 11, 1, 0, 0, 0);
    private final LocalDateTime END_DATE = LocalDateTime.of(2025, 11, 30, 11, 59, 59);

    @Test
    void 강의_정상_생성() {
        Session session = new Session(START_DATE, END_DATE, "paid",
                100, 300_000L, "pending", COVER_IMAGE);

        Assertions.assertThat(session.getSessionType()).isEqualTo(SessionType.PAID);
        Assertions.assertThat(session.getTuition()).isEqualTo(300_000L);
        Assertions.assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.PENDING);
        Assertions.assertThat(session.getMaxCapacity()).isEqualTo(100);
    }

    @Test
    void 무료_강의_정상_생성() {
        Session session = new Session(START_DATE, END_DATE, "free", "pending", COVER_IMAGE);

        Assertions.assertThat(session.getSessionType()).isEqualTo(SessionType.FREE);
        Assertions.assertThat(session.getMaxCapacity()).isEqualTo(Integer.MAX_VALUE);
        Assertions.assertThat(session.getTuition()).isEqualTo(0L);
    }
}
