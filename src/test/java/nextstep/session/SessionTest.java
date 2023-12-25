package nextstep.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.payments.domain.Payment;
import nextstep.session.domain.Image;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionDuration;
import nextstep.session.domain.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    private LocalDateTime started = LocalDateTime.of(2023, 12, 01, 0, 0, 0);
    private LocalDateTime ended = LocalDateTime.of(2023, 12, 31, 0, 0, 0);
    private Session session;

    @BeforeEach
    void setUp() {
        session = Session.createFreeSession("자바강의", new Image(),
            new SessionDuration(started, ended));
    }

    @Test
    @DisplayName("수강신청 성공하여 과정에 학생이 추가된다.")
    void sessionAddStudentTest() {
        session.changeToRecruit();
        session.enrollStudent(NsUserTest.JAVAJIGI);
        assertThat(session.getParticipants().getStudents().contains(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("강의가 생성되면 준비상태를 가진다.")
    void sessionDefaultStatusIsPrepare() {
        Session javaSession =
            Session.createFreeSession("자바강의", new Image(), new SessionDuration(started, ended));

        Session tddSession =
            Session.createPaidSession("TDD강의", new Image(), new SessionDuration(started, ended),
                1000, 10);

        assertThat(javaSession.getSessionStatus()).isEqualTo(SessionStatus.PREPARE);
        assertThat(tddSession.getSessionStatus()).isEqualTo(SessionStatus.PREPARE);
    }

    @Test
    @DisplayName("강의 상태가 모집중이 아니라면 예외를 반환한다.")
    void cannotEnrollIfNotRecruiting() {
        Payment payment = new Payment("payId", 1L, 1L, 0L);
        assertThatThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI, payment)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    @DisplayName("무료강의 상태가 모집중인 경우라면 수강신청이 가능하다.")
    void canEnrollFreeSessionRecruiting() {
        Session javaSession =
            Session.createFreeSession("자바강의", new Image(), new SessionDuration(started, ended));
        javaSession.changeToRecruit();
        javaSession.enrollStudent(NsUserTest.JAVAJIGI);

        assertThat(javaSession.getParticipants().getStudents()).contains(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("유료강의 상태가 모집중인 경우라면 수강신청이 가능하다.")
    void canEnrollPaidSessionRecruiting() {
        Payment payment = new Payment("payId", 1L, 1L, 1000L);
        Session tddSession =
            Session.createPaidSession("TDD강의", new Image(), new SessionDuration(started, ended),
                1000, 10);
        tddSession.changeToRecruit();
        tddSession.enrollStudent(NsUserTest.JAVAJIGI, payment);

        assertThat(tddSession.getParticipants().getStudents()).contains(NsUserTest.JAVAJIGI);
    }

}
