package nextstep.courses.service;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.UserEnrollment;
import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.courses.domain.enums.EnrollmentStatus;
import nextstep.courses.domain.enums.ImageType;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;
import nextstep.users.domain.UserTest;
import nextstep.users.exception.UserNotSelectedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

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
    @DisplayName("sessionId를 통해 Enrollment을 얻을 수 있다.")
    public void findEnrollmentBySessionId_test() {
        long sessionId = 300;
        Enrollment enrollment = new Enrollment(
                List.of(
                        new UserEnrollment(UserTest.JAVAJIGI, ApprovalStatus.PENDING),
                        new UserEnrollment(UserTest.SANJIGI, ApprovalStatus.PENDING),
                        new UserEnrollment(UserTest.INSTRUCTOR, ApprovalStatus.PENDING)
                )
                , EnrollmentStatus.ENROLLING,
                10);

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getEnrollment()).isEqualTo(enrollment);
    }

    @Test
    @DisplayName("sessionId를 통해 UserEnrollment을 얻을 수 있다.")
    public void findUserEnrollmentBySessionId_test() {
        long sessionId = 300;
        UserEnrollment userEnrollment = new UserEnrollment(UserTest.SANJIGI, ApprovalStatus.PENDING);

        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getEnrollment().getUserEnrollments()).contains(userEnrollment);
    }

    @Test
    @DisplayName("유저를 세션에 등록할 수 있다.")
    public void enrollUserToSession_test() {
        Session beforeSession = sessionService.findSessionById(300);
        User user = UserTest.DKSWNKK;

        sessionService.enrollUserToSession(beforeSession.getId(), user.getId());

        Session afterSession = sessionService.findSessionById(beforeSession.getId());
        Assertions.assertThat(afterSession.getUsers()).contains(user);
    }

    @Test
    @DisplayName("동일한 세션에 유저는 중복으로 등록할 수 없다.")
    public void userCannotEnrollTwiceInSameSession_test() {
        Session session = sessionService.findSessionById(300);
        User user = UserTest.DKSWNKK;

        sessionService.enrollUserToSession(session.getId(), user.getId());

        Assertions.assertThatThrownBy(() ->
                sessionService.enrollUserToSession(session.getId(), user.getId())
        ).isInstanceOf(SessionEnrollmentException.class);
    }

    @Test
    @DisplayName("강의가 진행 중인 상태에서도 유저는 등록할 수 있다.")
    public void test_3() {
        Session beforeSession = sessionService.findSessionById(400);
        User user = UserTest.JAVAJIGI;

        sessionService.enrollUserToSession(beforeSession.getId(), user.getId());

        Session afterSession = sessionService.findSessionById(beforeSession.getId());
        Assertions.assertThat(afterSession.getUsers()).contains(user);
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발되지 않은 인원은 수강 승인할 수 없다.")
    public void test_2() {
        User admin = UserTest.INSTRUCTOR;
        User unSelectedUser = UserTest.SANJIGI;
        Session session = sessionService.findSessionById(300);

        Assertions.assertThatThrownBy(() ->
                sessionService.approveUserToSession(admin.getId(), unSelectedUser.getId(), session.getId())
        ).isInstanceOf(UserNotSelectedException.class);
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발된 인원을 수강 승인 할 수 있다.")
    public void test() {
        User admin = UserTest.INSTRUCTOR;
        User selectedUser = UserTest.JAVAJIGI;
        Session session = sessionService.findSessionById(300);

        sessionService.approveUserToSession(admin.getId(), selectedUser.getId(), session.getId());

        Session afterSession = sessionService.findSessionById(session.getId());

        UserEnrollment selectedEnrollment = afterSession.getEnrollment().getUserEnrollments()
                .stream()
                .filter(userEnrollment -> userEnrollment.getUser().equals(selectedUser))
                .findFirst()
                .orElse(null);

        Assertions.assertThat(selectedEnrollment.getApprovalStatus()).isEqualTo(ApprovalStatus.APPROVED);
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발되지 않은 사람은 수강 취소할 수 있다.")
    public void test4() {
        User admin = UserTest.INSTRUCTOR;
        User unSelectedUser = UserTest.SANJIGI;
        Session session = sessionService.findSessionById(300);

        sessionService.disApproveUserToSession(admin.getId(), unSelectedUser.getId(), session.getId());

        Session afterSession = sessionService.findSessionById(session.getId());
        UserEnrollment selectedEnrollment = afterSession.getEnrollment().getUserEnrollments()
                .stream()
                .filter(userEnrollment -> userEnrollment.getUser().equals(unSelectedUser))
                .findFirst()
                .orElse(null);
        Assertions.assertThat(selectedEnrollment.getApprovalStatus()).isEqualTo(ApprovalStatus.CANCELED);
    }

}