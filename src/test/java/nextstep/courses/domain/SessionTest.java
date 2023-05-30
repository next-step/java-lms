package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DisplayName("세션 객체 테스트")
class SessionTest {

    LocalDateTime createDate;
    NsUser JAVAJIGI;
    NsUser SANJIGI;
    NsUser WOOK;
    SessionPeriod sessionPeriod;
    String name;
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
        name = "수강신청(도메인모델)";
        coverImage = new Image("test.jpeg", "http://nextstep.com");
        students = new Students();
        enrollment = new Enrollment(50, students);
    }

    @DisplayName("준비상태의 세션을 생성할때 참여중인 학생이 있으면 예외가 발생한다")
    @Test
    void createSessionException() {
        students.addStudent(WOOK);
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        new Session(name, sessionPeriod, SessionStatus.PREPARING, coverImage,  new FreePaymentStrategy(), enrollment))
                .withMessage("there should be no students when the session is in preparation");
    }

    @DisplayName("현재 세션에 참가한 학생의 숫자를 구할수 있다")
    @Test
    void currentEnrolmentCount() {
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage,  new FreePaymentStrategy(), enrollment);
        session.enrollInSession(JAVAJIGI);
        session.enrollInSession(SANJIGI);
        session.enrollInSession(WOOK);

        int currentEnrolmentStudentCount = session.currentEnrolmentCount();

        Assertions.assertThat(currentEnrolmentStudentCount).isEqualTo(3);
    }

    @DisplayName("현재 세션에 참가한 학생이 있는지 확인할수 있다(참가자가 없으면 false)")
    @Test
    void currentEnrolmentIsEmpty() {
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage,  new FreePaymentStrategy(), enrollment);

        boolean hasEnrolledStudent = session.hasEnrolledStudent();

        Assertions.assertThat(hasEnrolledStudent).isFalse();

    }

    @DisplayName("현재 세션에 참가한 학생이 있는지 확인할수 있다(참가자가 있으면 true) ")
    @Test
    void currentEnrolmentIsNotEmpty() {
        students.addStudent(WOOK);
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage, new FreePaymentStrategy(), enrollment);

        boolean hasEnrolledStudent = session.hasEnrolledStudent();

        Assertions.assertThat(hasEnrolledStudent).isTrue();
    }

    @DisplayName("현재 세션의 진행 기간을 구할수 있다")
    @Test
    void sessionDuration() {
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusDays(20));
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage, new FreePaymentStrategy(), enrollment);
        Assertions.assertThat(session.sessionDuration()).isEqualTo(20);
    }

    @DisplayName("무료 세션의 금액은 0원이다")
    @Test
    void freeSessionPrice() {
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage, new FreePaymentStrategy(), enrollment);
        Assertions.assertThat(session.sessionPrice()).isEqualTo(0);
    }

    @DisplayName("유료 세션의 금액은 생정할때 등록한 금액에 해당한다")
    @Test
    void paidSessionPrice() {
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage, new PaidPaymentStrategy(800000), enrollment);
        Assertions.assertThat(session.sessionPrice()).isEqualTo(800000);
    }
}
