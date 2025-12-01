package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.domain.session.constant.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    private final CoverImage COVER_IMAGE = new CoverImage(1, "png", 300, 200);
    private final LocalDateTime START_DATE = LocalDateTime.of(2025, 11, 1, 0, 0, 0);
    private final LocalDateTime END_DATE = LocalDateTime.of(2025, 11, 30, 11, 59, 59);

    @Test
    void 유료_강의_정상_생성() {
        Session session = new Session(1L, START_DATE, END_DATE, "paid",
                100, 300_000L, "pending", COVER_IMAGE);

        assertThat(session.getSessionPolicy().getSessionType()).isEqualTo(SessionType.PAID);
        assertThat(session.getSessionPolicy().getTuition()).isEqualTo(new Tuition(300_000L));
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.PENDING);
        assertThat(session.getSessionPolicy().getMaxCapacity()).isEqualTo(new Capacity(100));
    }

    @Test
    void 무료_강의_정상_생성() {
        Session session = new Session(1L, START_DATE, END_DATE, "free", "pending", COVER_IMAGE);

        assertThat(session.getSessionPolicy().getSessionType()).isEqualTo(SessionType.FREE);
        assertThat(session.getSessionPolicy().getMaxCapacity()).isEqualTo(new Capacity(Integer.MAX_VALUE));
        assertThat(session.getSessionPolicy().getTuition()).isEqualTo(new Tuition(0L));
    }

    @Test
    void 수강신청시_모집중이_아닐경우_예외발생() {
        Session session = new Session(2L, START_DATE, END_DATE, "paid",
                100, 300_000L, "pending", COVER_IMAGE);
        Enrollment enrollment = new Enrollment(NsUserTest.JAVAJIGI, 2L, new Payment(2L, 1L, 300_000L));

        assertThatThrownBy(() -> session.addEnrollment(enrollment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재는 강의 모집중이 아닙니다.");
    }

    @Test
    void 유료강의_최대_수강인원_초과시_예외발생() {
        Session session = new Session(1L, START_DATE, END_DATE, "paid",
                1, 300_000L, "active", COVER_IMAGE);
        Enrollment enrollment1 = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));
        Enrollment enrollment2 = new Enrollment(NsUserTest.SANJIGI, 1L, new Payment(1L, 2L, 300_000L));

        session.addEnrollment(enrollment1);

        assertThatThrownBy(() -> session.addEnrollment(enrollment2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강인원이 초과했습니다.");
    }

    @Test
    void 중복_수강신청시_예외발생() {
        Session session = new Session(1L, START_DATE, END_DATE, "paid",
                100, 300_000L, "active", COVER_IMAGE);
        Enrollment enrollment1 = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));
        Enrollment enrollment2 = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));

        session.addEnrollment(enrollment1);

        assertThatThrownBy(() -> session.addEnrollment(enrollment2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 신청한 강의입니다.");
    }

}
