package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.image.SessionImage;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {

    private NsUser student = NsUserTest.JAVAJIGI;
    public static Course course = new Course(1L, "TDD with java", 1L);

    @Test
    @DisplayName("강의는 시작일과 종료일이 없는 경우 Exception Throw")
    void
    notHave_StartAndEndDate_Test() {
        assertThrows(IllegalArgumentException.class,
                () -> Session.valueOf(1L, "과제4-리팩토링", course.getId(), EnrollmentStatus.RECRUITING
                        , null, null, LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void haveDateTest() {
        assertDoesNotThrow(()
                -> Session.valueOf(1L, "과제4-리팩토링", course.getId(), EnrollmentStatus.PREPARING
                , LocalDate.of(2023, 12, 01), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void saveSessionImageTest() throws InvalidImageFormatException {
        Session session = Session.valueOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.PREPARING
                , LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        assertThat(session.hasImage()).isFalse();

        SessionImage 강의_이미지 = SessionImage.valueOf(1L, session, 1024 * 1024, 300, 200, "jpg");
        session.saveImage(강의_이미지);

        assertThat(session.hasImage()).isTrue();
    }

    @Test
    @DisplayName("강의가 준비중인 경우 수강신청을 하면 Exception Throw")
    void cannotSignUp_ForPreparingSession_Test() {
        Session session = Session.valueOf(1L, "lms", course.getId(), EnrollmentStatus.PREPARING
                , LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Payment payment = Payment.freeOf("1", session.getSessionId(), student.getId());
        assertThrows(CannotSignUpException.class, () -> session.signUp(student, payment));
    }

    @Test
    @DisplayName("강의가 종료상태인 경우 수강신청을 하면 Exception Throw")
    void cannotSignUp_ForClosedSession_Test() {
        Session session = Session.valueOf(1L, "LMS", course.getId(), EnrollmentStatus.CLOSE
                , LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Payment payment = Payment.freeOf("1", session.getSessionId(), student.getId());
        assertThrows(CannotSignUpException.class, () -> session.signUp(student, payment));
    }

    @Test
    @DisplayName("강의가 준비상태인 경우 수강신청이 가능하다.")
    void signUp_ForRecruitingSession_Test() throws CannotSignUpException {
        Session session = Session.valueOf(1L,"lms", course.getId(), EnrollmentStatus.RECRUITING,
                LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Payment payment = Payment.freeOf("1", session.getSessionId(), student.getId());
        session.signUp(student, payment);
        assertThat(session.getStudentCount()).isEqualTo(1);
    }
}
