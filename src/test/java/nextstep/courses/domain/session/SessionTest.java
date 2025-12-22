package nextstep.courses.domain.session;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentCandidate;
import nextstep.courses.domain.image.SessionImage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    private static final SessionImage IMAGE = new SessionImage(500_000L, "png", 300, 200);

    @Test
    public void 정상적인_강의_생성() {
        Session session = new Session(START_DATE, END_DATE, IMAGE);

        assertThat(session).isNotNull();
    }

    @Test
    public void 기수_정보를_가진_강의_생성() {
        int cohort = 1;

        Session session = new Session(cohort, START_DATE, END_DATE, IMAGE, ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING, new FreeSessionType());

        assertThat(session.getCohort()).isEqualTo(cohort);
    }


    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Session session = new Session(1L, 1, START_DATE, END_DATE, IMAGE,
                ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        Enrollment enrollment = session.createEnrollment(Collections.emptyList());
        EnrollmentCandidate candidate = enrollment.enroll(1L, null);

        assertThat(candidate.getNsUserId()).isEqualTo(1L);
        assertThat(candidate.getSessionId()).isEqualTo(1L);
    }


    @Test
    void 진행중이면서_모집중일때_수강신청_가능() {
        Session session = new Session(1L, 1, START_DATE, END_DATE, IMAGE,
                ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING, new FreeSessionType());

        Enrollment enrollment = session.createEnrollment(java.util.Collections.emptyList());
        EnrollmentCandidate candidate = enrollment.enroll(1L, null);

        assertThat(candidate.getNsUserId()).isEqualTo(1L);
        assertThat(candidate.getSessionId()).isEqualTo(1L);
    }

    @Test
    void 기존_SessionStatus만있어도_생성_가능() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 31);
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);

        Session session = new Session(1L, 1, startDate, endDate, image,
                ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());

        assertThat(session.getRecruitmentStatus()).isEqualTo(RecruitmentStatus.RECRUITING);
    }


}
