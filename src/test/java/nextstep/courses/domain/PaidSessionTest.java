package nextstep.courses.domain;

import nextstep.courses.SessionDeadLineException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        Session session = new PaidSession(
                period,
                thumbnail,
                limitStudents,
                students);

        assertThrows(SessionDeadLineException.class, () -> session.apply(new Payment()), "수강 신청 인원이 마감 되었습니다.");
    }
}
