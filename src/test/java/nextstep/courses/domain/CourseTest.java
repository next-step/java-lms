package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CourseTest {

    private Course course;
    private Session session1;
    private Session session2;
    private Session session3;

    @BeforeEach
    void setUp() {
        this.course = new Course();
        Long courseId = 1L;
        SessionInfo sessionInfo = new SessionInfo(courseId, 1L, "titl1", "imageInfo", SessionType.FREE);
        SessionStatus sessionStatus = SessionStatus.OPENED;
        SessionTimeLine sessionTimeLine = new SessionTimeLine(LocalDateTime.now(), LocalDateTime.now().plusDays(10));

        session1 = new Session(sessionInfo, sessionStatus, sessionTimeLine, 3L);
        session2 = new Session(sessionInfo, sessionStatus, sessionTimeLine, 3L);
        session3 = new Session(sessionInfo, sessionStatus, sessionTimeLine, 3L);

        course.addSession(session1);
        course.addSession(session2);
        course.addSession(session3);
    }

    @Test
    @DisplayName("기수에 따른 강의를 조회할 수 있다.")
    void findCourse_DependOnId() {
        Session session = course.getSession(1);

        Assertions.assertThat(session).isEqualTo(this.session1);
    }


}
