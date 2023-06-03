package nextstep.courses.domain;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.payment.FreePaymentStrategy;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionInformation;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DisplayName("과정 객체 테스트")
class CourseTest {

    LocalDateTime createDate;
    NsUser JAVAJIGI;
    NsUser SANJIGI;
    NsUser WOOK;
    SessionPeriod sessionPeriod;
    SessionStatus sessionStatus;
    Image coverImage;
    Enrollment enrollment;

    @BeforeEach
    void init() {
        createDate = LocalDateTime.of(2023, 5, 27, 19, 30, 30);
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null);
        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null);
        WOOK = new NsUser(3L, "wook", "password", "name", "wook@gmail.com");
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusDays(60));
        sessionStatus = SessionStatus.ENROLLING;
        coverImage = new Image("test.jpeg", "http://nextstep.com");
        enrollment = new Enrollment(50);
    }

    @DisplayName("과정에 세션을 추가 할 수 있다")
    @Test
    void addSessionInCourse() {
        SessionInformation sessionInformation1 = new SessionInformation("TDD", 16);
        SessionInformation sessionInformation2 = new SessionInformation("TDD", 17);
        Session session1 = new Session(1L, sessionInformation1, sessionPeriod, sessionStatus, coverImage, new FreePaymentStrategy(), enrollment);
        Session session2 = new Session(1L, sessionInformation2, sessionPeriod, sessionStatus, coverImage, new FreePaymentStrategy(), enrollment);
        Course course = new Course("test course", 1L);
        course.addSession(session1);
        course.addSession(session2);

        assertThat(course.sessionCount()).isEqualTo(2);
        assertThat(course.fetchSessions())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(session1, session2);
    }
}
