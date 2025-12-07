package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    private static final SessionImage IMAGE = new SessionImage(500_000L, "png", 300, 200);

    @Test
    public void 정상적인_강의_생성() {
        Session session = new Session(START_DATE, END_DATE, IMAGE, "준비중");

        assertThat(session).isNotNull();
    }

    @Test
    public void 기수_정보를_가진_강의_생성() {
        int cohort = 1;

        Session session = new Session(cohort, START_DATE, END_DATE, IMAGE);

        assertThat(session.getCohort()).isEqualTo(cohort);
    }


    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Session session = new Session(1L, 1, START_DATE, END_DATE, IMAGE, new Enrollment(SessionStatus.RECRUITING, new FreeSessionType()));
        Enrollment enrollment = session.createEnrollment(Collections.emptyList());
        EnrolledStudent student = enrollment.enroll(1L, null);

        assertThat(student.getNsUserId()).isEqualTo(1L);
        assertThat(student.getSessionId()).isEqualTo(1L);
    }


}
