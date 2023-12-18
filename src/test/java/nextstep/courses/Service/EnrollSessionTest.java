package nextstep.courses.Service;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.Session;
import nextstep.courses.service.EnrollSessionService;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnrollSessionTest {

    private Course course = new Course(1L, "TDD with java 17", 1L);
    private Session session1 = PaidSession.feeOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.RECRUITING
            , LocalDate.of(2023, 12, 01), LocalDate.of(2023, 12, 30)
            , LocalDateTime.now(), null, 10, 10_000L);
    private Session session2 = FreeSession.valueOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.CLOSE
            , LocalDate.of(2023, 12, 01), LocalDate.of(2023, 12, 30)
            , LocalDateTime.now(), null);

    private Session session3 = FreeSession.valueOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.RECRUITING
            , LocalDate.of(2024, 03, 01), LocalDate.of(2024, 04, 30)
            , LocalDateTime.now(), null);

    private Session session4 = FreeSession.valueOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.CLOSE
            , LocalDate.of(2024, 03, 01), LocalDate.of(2024, 04, 30)
            , LocalDateTime.now(), null);

    private Payment payment = new Payment("pay1", session1.getId(), NsUserTest.JAVAJIGI.getId(), 10_000L);

    @Test
    @DisplayName("강의가 진행중에 비모집중인 경우 Exception throw")
    void canNotEnrollInProgressTest() {
        EnrollSessionService enrollSessionService = new EnrollSessionService();

        assertThrows(CannotSignUpException.class, () -> enrollSessionService.enrollSession(session2, NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("강의가 진행중에 모집중인 경우 수강신청 가능")
    void canEnrollInProgressTest() {
        EnrollSessionService enrollSessionService = new EnrollSessionService();

        assertDoesNotThrow(() -> enrollSessionService.enrollSession(session1, NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("강의가 준비중일 때 모집중인 경우 수강신청 가능")
    void canNotEnrollNotStartedTest() {
        EnrollSessionService enrollSessionService = new EnrollSessionService();

        assertDoesNotThrow(() -> enrollSessionService.enrollSession(session3, NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("강의가 준비중일 때 모집중인 경우 수강신청 가능")
    void canEnrollNotStartedTest() {
        EnrollSessionService enrollSessionService = new EnrollSessionService();

        assertThrows(CannotSignUpException.class, () -> enrollSessionService.enrollSession(session4, NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("수강신청한 사람 중 선발되지 않은 수강생은 수강 취소가 된다.")
    void cancelSessionTest() {
        EnrollSessionService enrollSessionService = new EnrollSessionService();
        Student student = enrollSessionService.enrollSession(session3, NsUserTest.SANJIGI);

        enrollSessionService.cancelSession(session3, student);
    }
}
