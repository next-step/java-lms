package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.InvalidImageFormatException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {

    @Test
    @DisplayName("강의는 시작일과 종료일이 없는 경우 Exception Throw")
    void notHave_StartAndEndDate_Test() {


    }

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void haveDateTest() {

    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void saveSessionImageTest() throws InvalidImageFormatException {
        Session session = Session.titleOf("과제4 - 레거시 리팩토링");
        assertThat(session.hasImage()).isFalse();

        SessionImage 강의_이미지 = SessionImage.nameOf("강의 이미지");
        session.imageOf(강의_이미지);

        assertThat(session.hasImage()).isTrue();
    }

    @Test
    @DisplayName("강의가 준비중인 경우 수강신청을 하면 Exception Throw")
    void cannotSignUp_ForPreparingSession_Test() throws CannotSignUpException {
        Payment payment = Payment.freeOf("1");
        Session session = Session.statusOf("lms", "TDD", SessionStatus.PREPARING);
        assertThrows(CannotSignUpException.class, () -> session.signUp(payment));
    }

    @Test
    @DisplayName("강의가 종료상태인 경우 수강신청을 하면 Exception Throw")
    void cannotSignUp_ForClosedSession_Test() throws CannotSignUpException {
        Payment payment = Payment.freeOf("1");
        Session session = Session.statusOf("lms", "TDD", SessionStatus.CLOSE);
        assertThrows(CannotSignUpException.class, () -> session.signUp(payment));
    }

    @Test
    @DisplayName("강의가 준비상태인 경우 수강신청이 가능하다.")
    void signUp_ForRecruitingSession_Test() throws CannotSignUpException {
        Payment payment = Payment.freeOf("1");
        Session session = Session.statusOf("lms", "TDD", SessionStatus.RECRUITING);
        session.signUp(payment);
        assertThat(session.getStudentCount()).isEqualTo(1);
    }
}
