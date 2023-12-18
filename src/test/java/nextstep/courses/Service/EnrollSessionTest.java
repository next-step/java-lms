package nextstep.courses.Service;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.Session;
import nextstep.courses.service.EnrollSessionService;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EnrollSessionTest {

    private Course course = new Course(1L, "TDD with java 17", 1L);
    private Session session1 = PaidSession.feeOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.RECRUITING
            , LocalDate.of(2023, 12, 01), LocalDate.of(2023, 12, 30)
            , LocalDateTime.now(), null, 10, 10_000L);
    private Session session2 = FreeSession.valueOf(1L, "과제4 - 레거시 리팩토링", course.getId(), EnrollmentStatus.CLOSE
            , LocalDate.of(2023, 12, 01), LocalDate.of(2023, 12, 30)
            , LocalDateTime.now(), null);

    private Payment payment = new Payment("pay1", session1.getId(), NsUserTest.JAVAJIGI.getId(), 10_000L);

    @Test
    @DisplayName("강의가 진행중에 비모집중인 경우 Exception throw")
    void canEnrollSessionTest() {
        EnrollSessionService enrollSessionService = new EnrollSessionService();

        assertThrows(CannotSignUpException.class, () -> enrollSessionService.enrollSession(session2, NsUserTest.SANJIGI));
    }
}
