package nextstep.courses.domain.course;

import nextstep.courses.domain.session.FreeSessionType;
import nextstep.courses.domain.session.ProgressStatus;
import nextstep.courses.domain.session.RecruitmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.image.SessionImage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    private static final SessionImage IMAGE = new SessionImage(500_000L, "png", 300, 200);

    @Test
    public void 세션을_가진_과정_생성() {

        Session session1 = new Session(1, START_DATE, END_DATE, IMAGE, ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING, new FreeSessionType());
        Session session2 = new Session(2, START_DATE, END_DATE, IMAGE, ProgressStatus.PREPARING, RecruitmentStatus.NOT_RECRUITING, new FreeSessionType());
        Sessions sessions = new Sessions(new ArrayList<>());
        sessions.add(session1);
        sessions.add(session2);

        Course course = new Course("TDD클린코드", 1L, sessions);

        assertThat(course.getSessions()).isNotNull();
        assertThat(course.getSessions().size()).isEqualTo(2);
    }

    @Test
    public void 과정에_새로운_기수_추가() {

        Session session1 = new Session(1, START_DATE, END_DATE, IMAGE,ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING,new FreeSessionType());
        Sessions sessions = new Sessions(new ArrayList<>());
        sessions.add(session1);

        Course course = new Course("JPA의사실과 오해", 1L, sessions);

        Session session2 = new Session(2, START_DATE, END_DATE, IMAGE,ProgressStatus.PREPARING, RecruitmentStatus.RECRUITING,new FreeSessionType());
        course.addSession(session2);

        assertThat(course.getSessions().size()).isEqualTo(2);
    }

}
