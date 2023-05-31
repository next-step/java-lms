package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {
    @Test
    @DisplayName("하나의 과정은 여러 개의 강의를 가질 수 있다")
    void test01() {
        //given
        Capacity capacity01 = new Capacity(9, 10);
        CoverImage coverImage01 = new CoverImage(1L, "강의1사진", "http://");
        Session session01 = new Session(1L, "강의1", "20230531", "20230605", capacity01, coverImage01, SessionType.FREE, SessionStatus.RECRUTING, 1L);

        Capacity capacity02 = new Capacity(9, 10);
        CoverImage coverImage02 = new CoverImage(1L, "강의1사진", "http://");
        SessionStatus sessionStatus = SessionStatus.RECRUTING;
        Session session02 = new Session(1L, "강의1", "20230531", "20230605", capacity02, coverImage02, SessionType.FREE, SessionStatus.RECRUTING, 1L);

        Sessions sessions = new Sessions();
        sessions.add(session01);
        sessions.add(session02);

        //when
        Course course = new Course("title1", 1L);
        course.addSessions(sessions);

        //then
        Assertions.assertThat(course.getSessions().size()).isEqualTo(2);
    }

}
