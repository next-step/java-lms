package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DisplayName("과정 객체 테스트")
class CourseTest {

    LocalDateTime createDate;
    NsUser JAVAJIGI;
    NsUser SANJIGI;
    NsUser WOOK;
    SessionPeriod sessionPeriod;
    String name;
    Image coverImage;
    Students students;

    @BeforeEach
    void init() {
        createDate = LocalDateTime.of(2023, 5, 27, 19, 30, 30);
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null);
        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null);
        WOOK = new NsUser(3L, "wook", "password", "name", "wook@gmail.com");
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusDays(60));
        name = "수강신청(도메인모델)";
        coverImage = new Image("test.jpeg", "http://nextstep.com");
        students = new Students(100);
    }

    @DisplayName("과정에 세션을 추가 할 수 있다")
    @Test
    void addSessionInCourse() {
        Session session1 = new Session(sessionPeriod, name, SessionStatus.ENROLLING, coverImage, students, new FreePaymentStrategy());
        Session session2 = new Session(sessionPeriod, name, SessionStatus.PREPARING, coverImage, students, new FreePaymentStrategy());
        Course course = new Course("test course", new Term(11), 1L);
        course.addSession(session1);
        course.addSession(session2);

        Assertions.assertThat(course.sessionCount()).isEqualTo(2);
        Assertions.assertThat(course.termValue()).isEqualTo("11기");
        Assertions.assertThat(course.fetchSessions())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(session1,session2);
    }
}
