package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionImage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SessionsTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    private static final SessionImage IMAGE = new SessionImage(500_000L, "png", 300, 200);

    @Test
    public void 세션_목록_생성() {
        Session session1 = new Session(1, START_DATE, END_DATE, IMAGE, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        Session session2 = new Session(2, START_DATE, END_DATE, IMAGE, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());

        Sessions sessions = new Sessions(List.of(session1, session2));

        assertThat(sessions).isNotNull();
    }

    @Test
    public void 세션_추가() {
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);

        Session session1 = new Session(1, START_DATE, END_DATE, image, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        Sessions sessions = new Sessions(List.of(session1));

        Session session2 = new Session(2, START_DATE, END_DATE, image, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        sessions.add(session2);

        assertThat(sessions.size()).isEqualTo(2);
    }

    @Test
    public void 특정_기수_세션_조회() {
        Session session1 = new Session(1, START_DATE, END_DATE, IMAGE, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        Session session2 = new Session(2, START_DATE, END_DATE, IMAGE, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        Sessions sessions = new Sessions(List.of(session1, session2));

        Session found = sessions.findByCohort(2);

        assertThat(found).isEqualTo(session2);
        assertThat(found.getCohort()).isEqualTo(2);
    }

}
