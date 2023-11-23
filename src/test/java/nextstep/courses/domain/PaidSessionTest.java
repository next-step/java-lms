package nextstep.courses.domain;

import nextstep.courses.IncorrectAmountException;
import nextstep.courses.SessionDeadLineException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaidSessionTest {

    @Test
    @DisplayName("최대 인원까지 차오른 강의를 신청하면 예외 처리 한다")
    void isSupport() {
        Period period = new Period(LocalDate.of(2023, 11, 24),
                LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail("테스트",
                "/home/test.png",
                new Volume(1024L),
                new Size(new Width(300),
                        new Height(200)));
        Students limitStudents = new Students(0);
        Students students = new Students();
        Amount amount = new Amount(20000L);
        Session session = new PaidSession(
                period,
                thumbnail,
                limitStudents,
                students,
                amount);

        assertThrows(SessionDeadLineException.class, () -> session.apply(new Payment()), "수강 신청 인원이 마감 되었습니다.");
    }

    @Test
    @DisplayName("결제 금액과 강의 금액이 다르면 예외 처리 한다")
    void apply() {
        Period period = new Period(LocalDate.of(2023, 11, 24),
                LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail("테스트",
                "/home/test.png",
                new Volume(1024L),
                new Size(new Width(300),
                        new Height(200)));
        Students limitStudents = new Students(1);
        Students students = new Students();
        Amount amount = new Amount(20000L);
        Session session = new PaidSession(
                period,
                thumbnail,
                limitStudents,
                students,
                amount);

        assertThrows(IncorrectAmountException.class, () -> session.apply(new Payment("테스트", 0L, 0L, 15000L)),
                "결제 금액과 강의 금액이 다릅니다.");
    }

    @Test
    @DisplayName("유료 강의 신청을 한다")
    void apply2() {
        Period period = new Period(LocalDate.of(2023, 11, 24),
                LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail("테스트",
                "/home/test.png",
                new Volume(1024L),
                new Size(new Width(300),
                        new Height(200)));
        Students limitStudents = new Students(1);
        Students students = new Students();
        Amount amount = new Amount(20000L);
        Session session = new PaidSession(
                period,
                thumbnail,
                limitStudents,
                students,
                amount);

        assertDoesNotThrow(() -> session.apply(new Payment("테스트", 0L, 0L, 20000L)));
    }
}
