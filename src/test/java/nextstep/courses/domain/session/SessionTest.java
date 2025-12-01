package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.builder.EnrollmentBuilder;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.domain.session.builder.SessionPolicyBuilder;
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
        Session sessionBuilder = new SessionBuilder().build();

        assertThat(sessionBuilder.getSessionPolicy().getSessionType()).isEqualTo(SessionType.PAID);
        assertThat(sessionBuilder.getSessionPolicy().getTuition()).isEqualTo(new Tuition(300_000L));
        assertThat(sessionBuilder.getSessionStatus()).isEqualTo(SessionStatus.PENDING);
        assertThat(sessionBuilder.getSessionPolicy().getMaxCapacity()).isEqualTo(new Capacity(100));
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
        Session session = new SessionBuilder().withSessionStatus(SessionStatus.PENDING).build();
        Enrollment enrollment = new EnrollmentBuilder().build();

        assertThatThrownBy(() -> session.addEnrollment(enrollment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재는 강의 모집중이 아닙니다.");
    }

    @Test
    void 유료강의_최대_수강인원_초과시_예외발생() {
        Session session = new SessionBuilder()
                .withSessionPolicy(new SessionPolicyBuilder().withMaxCapacity(1).build())
                .withSessionStatus(SessionStatus.ACTIVE)
                .withEnrollment(new EnrollmentBuilder().build())
                .build();

        Enrollment newEnrollment = new Enrollment(NsUserTest.SANJIGI, 1L, new Payment(1L, 2L, 300_000L));

        assertThatThrownBy(() -> session.addEnrollment(newEnrollment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강인원이 초과했습니다.");
    }

}
