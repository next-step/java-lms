package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Students;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.payment.FreePaymentStrategy;
import nextstep.courses.domain.payment.PaidPaymentStrategy;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionInformation;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("세션 객체 테스트")
class SessionTest {

    LocalDateTime createDate;
    NsUser JAVAJIGI;
    NsUser SANJIGI;
    NsUser WOOK;
    SessionPeriod sessionPeriod;
    SessionInformation sessionInformation;
    SessionStatus sessionStatus;
    Image coverImage;
    Students students;
    Enrollment enrollment;

    @BeforeEach
    void init() {
        createDate = LocalDateTime.of(2023, 5, 27, 19, 30, 30);
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null);
        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null);
        WOOK = new NsUser(3L, "wook", "password", "name", "wook@gmail.com");
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusDays(60));
        sessionStatus = SessionStatus.ENROLLING;
        sessionInformation = new SessionInformation("TDD Clean Code", 16);
        coverImage = new Image("test.jpeg", "http://nextstep.com");
        students = new Students(50);
        enrollment = new Enrollment(50);
    }

    @DisplayName("현재 세션에 참가한 학생이 있는지 확인할수 있다(참가자가 없으면 false)")
    @Test
    void currentEnrolmentIsEmpty() {
        Session session = new Session(sessionInformation, sessionPeriod, sessionStatus, coverImage,  new FreePaymentStrategy(), enrollment);

        boolean hasEnrolledStudent = session.hasEnrolledStudent();

        assertThat(hasEnrolledStudent).isFalse();

    }

    @DisplayName("현재 세션에 참가한 학생이 있는지 확인할수 있다(참가자가 있으면 true) ")
    @Test
    void currentEnrolmentIsNotEmpty() {
        Session session = new Session(sessionInformation, sessionPeriod, sessionStatus, coverImage, new FreePaymentStrategy(), enrollment);
        session.enrollInSession(WOOK);

        boolean hasEnrolledStudent = session.hasEnrolledStudent();

        assertThat(hasEnrolledStudent).isTrue();
    }

    @DisplayName("현재 세션의 진행 기간을 구할수 있다")
    @Test
    void sessionDuration() {
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusDays(20));
        Session session = new Session(sessionInformation, sessionPeriod, sessionStatus, coverImage, new FreePaymentStrategy(), enrollment);
        assertThat(session.sessionDuration()).isEqualTo(20);
    }

    @DisplayName("무료 세션의 금액은 0원이다")
    @Test
    void freeSessionPrice() {
        Session session = new Session(sessionInformation, sessionPeriod, sessionStatus, coverImage, new FreePaymentStrategy(), enrollment);
        assertThat(session.sessionCharge()).isEqualTo(0);
    }

    @DisplayName("유료 세션의 금액은 세션을 생성할때 등록한 금액에 해당한다")
    @Test
    void paidSessionPrice() {
        Session session = new Session(sessionInformation, sessionPeriod, sessionStatus, coverImage, new PaidPaymentStrategy(800000), enrollment);
        assertThat(session.sessionCharge()).isEqualTo(800000);
    }

    @DisplayName("세션의 타이틀을 확인 할 수 있다")
    @Test
    void sessionTitle() {
        Session session = new Session(sessionInformation, sessionPeriod, sessionStatus, coverImage, new PaidPaymentStrategy(800000), enrollment);
        assertThat(session.sessionTitle()).isEqualTo("TDD Clean Code 16기");
    }

    @DisplayName("세션의 상태가 준비중이면 강의 신청시 예외가 발생한다")
    @Test
    void preparingSessionEnroll() {
        SessionStatus status = SessionStatus.PREPARING;
        Session session = new Session(sessionInformation, sessionPeriod, status, coverImage, new PaidPaymentStrategy(800000), enrollment);
        Assertions.assertThatExceptionOfType(CannotEnrollException.class)
                .isThrownBy(() -> session.enrollInSession(WOOK))
                .withMessage("the current session is not in the enrolling status");
    }

    @DisplayName("세션의 상태가 종료면 강의 신청시 예외가 발생한다")
    @Test
    void finishedSessionEnroll() {
        SessionStatus status = SessionStatus.FINISHED;
        Session session = new Session(sessionInformation, sessionPeriod, status, coverImage, new PaidPaymentStrategy(800000), enrollment);
        Assertions.assertThatExceptionOfType(CannotEnrollException.class)
                .isThrownBy(() -> session.enrollInSession(WOOK))
                .withMessage("the current session is not in the enrolling status");
    }

    @DisplayName("세션의 상태가 모집중이면 강의 신청이 정상적으로 진행된다")
    @Test
    void enrollSessionStatus() {
        SessionStatus status = SessionStatus.ENROLLING;
        Session session = new Session(sessionInformation, sessionPeriod, status, coverImage, new PaidPaymentStrategy(800000), enrollment);
        session.enrollInSession(WOOK);
        assertThat(session.currentEnrolmentCount()).isEqualTo(1);
    }
}
