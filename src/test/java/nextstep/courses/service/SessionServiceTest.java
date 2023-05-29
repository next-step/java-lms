package nextstep.courses.service;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.enums.ImageType;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;
import nextstep.users.domain.UserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class SessionServiceTest {
    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("sessionId를 통해 session을 얻을 수 있다.")
    public void findSessionBySessionId_test() {
        long sessionId = 300;
        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getId()).isEqualTo(sessionId);
    }

    @Test
    @DisplayName("sessionId를 통해 Image를 얻을 수 있다.")
    public void findImageBySessionId_test() throws URISyntaxException {
        long sessionId = 300;
        Image image = new Image(200L, "사진", new URI("https://edu.nextstep.camp/"), 100L, ImageType.of("JPEG"),
                LocalDateTime.of(2023, 5, 29, 12, 34, 56),
                LocalDateTime.of(2023, 5, 30, 12, 34, 56));

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getCoverImage()).isEqualTo(image);
    }

    @Test
    @DisplayName("sessionId를 통해 sessionType을 얻을 수 있다.")
    public void findSessionTypeBySessionId_test() {
        long sessionId = 300;
        SessionType sessionType = SessionType.FREE;

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getSessionType()).isEqualTo(sessionType);
    }

    @Test
    @DisplayName("sessionId를 통해 sessionStatus을 얻을 수 있다.")
    public void findSessionStatusBySessionId_test() {
        long sessionId = 300;
        SessionStatus sessionStatus = SessionStatus.RECRUITING;

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getSessionStatus()).isEqualTo(sessionStatus);
    }

    @Test
    @DisplayName("sessionId를 통해 sessionTime을 얻을 수 있다.")
    public void findSessionTimeBySessionId_test() {
        long sessionId = 300;
        SessionTime sessionTime = new SessionTime(
                LocalDateTime.of(2023, 5, 29, 12, 34, 56),
                LocalDateTime.of(2023, 5, 30, 12, 34, 56)
        );

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getSessionTime()).isEqualTo(sessionTime);
    }

    @Test
    @DisplayName("sessionId를 통해 Enrollemt을 얻을 수 있다.")
    public void findEnrollmentBySessionId_test() {
        long sessionId = 300;
        Enrollment enrollment = new Enrollment(10);

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getEnrollment()).isEqualTo(enrollment);
    }

    @Test
    @DisplayName("유저를 세션에 등록할 수 있다.")
    public void enrollUserToSession_test() {
        Session beforeSession = sessionService.findSessionById(300);
        User user = UserTest.JAVAJIGI;

        sessionService.enrollUserToSession(beforeSession.getId(), user.getId());

        Session afterSession = sessionService.findSessionById(beforeSession.getId());
        Assertions.assertThat(afterSession.getUsers()).contains(user);
    }

    @Test
    @DisplayName("동일한 세션에 유저는 중복으로 등록할 수 없다.")
    public void userCannotEnrollTwiceInSameSession_test() {
        Session session = sessionService.findSessionById(300);
        User user = UserTest.JAVAJIGI;

        sessionService.enrollUserToSession(session.getId(), user.getId());

        Assertions.assertThatThrownBy(() ->
                sessionService.enrollUserToSession(session.getId(), user.getId())
        ).isInstanceOf(SessionEnrollmentException.class);
    }

}