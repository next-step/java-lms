package nextstep.courses.domain;

import nextstep.courses.domain.code.Enrollment;
import nextstep.courses.domain.code.EnrollmentStatus;
import nextstep.courses.domain.code.SessionStatus;
import nextstep.courses.exception.CanNotApplySessionStatusException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionTest {

    @Test
    @DisplayName("모집중이 아니면 예외 처리 된다.")
    void apply() {
        Period period = new Period(LocalDate.of(2023, 11, 24), LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail(0L, 0L, "테스트", "/home/test.png", new FileSize(1024L), new ImageSize(300L,
                200L));
        Amount amount = new Amount(20000L);

        Assertions.assertAll(() -> {
            Session session = new Session(0L, 0L, "테스트 타이틀", period, thumbnail, SessionStatus.PREPARING,
                    Enrollment.PAID, EnrollmentStatus.RECRUITING, 1, amount, LocalDateTime.now(), null);

            assertThrows(CanNotApplySessionStatusException.class, () -> session.enroll(new Payment("", 0L, 0L, 20000L), new NsUser(), new Students()), "수강 신청이 가능한 상태가 아닙니다.");
        }, () -> {
            Session session = new Session(0L, 0L, "테스트 타이틀", period, thumbnail, SessionStatus.END, Enrollment.PAID, EnrollmentStatus.RECRUITING, 1, amount, LocalDateTime.now(), null);

            assertThrows(CanNotApplySessionStatusException.class, () -> session.enroll(new Payment("", 0L, 0L, 20000L), new NsUser(), new Students()), "수강 신청이 가능한 상태가 아닙니다.");
        });


    }

    @Test
    @DisplayName("유료 강의 신청을 한다")
    void apply2() {
        Period period = new Period(LocalDate.of(2023, 11, 24), LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail(0L, 0L, "테스트", "/home/test.png", new FileSize(1024L), new ImageSize(300L, 200L));
        Amount amount = new Amount(20000L);

        Session session = new Session(0L, 0L, "테스트 타이틀", period, thumbnail, SessionStatus.RECRUITING, Enrollment.PAID, EnrollmentStatus.RECRUITING, 1, amount, LocalDateTime.now(), null);

        assertDoesNotThrow(() -> session.enroll(new Payment("", 0L, 0L, 20000L), new NsUser(0L, "테스트", "테스트", "테스트", "테스트"), new Students()));
    }

    @Test
    @DisplayName("무료 강의 신청을 한다")
    void apply3() {
        Period period = new Period(LocalDate.of(2023, 11, 24), LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail(0L, 0L, "테스트", "/home/test.png", new FileSize(1024L), new ImageSize(300L, 200L));
        Amount amount = new Amount(0L);

        Session session = new Session(0L, 0L, "테스트 타이틀", period, thumbnail, SessionStatus.RECRUITING, Enrollment.FREE, EnrollmentStatus.RECRUITING, 1, amount, LocalDateTime.now(), null);

        assertDoesNotThrow(() -> session.enroll(new Payment("", 0L, 0L, 20000L), new NsUser(0L, "테스트", "테스트", "테스트", "테스트"), new Students()));
    }
}
