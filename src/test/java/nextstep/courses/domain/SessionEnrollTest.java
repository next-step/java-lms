package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.PaidType.FREE;
import static nextstep.courses.domain.PaidType.PAID;
import static nextstep.courses.domain.SessionStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionEnrollTest {

    private static LocalDateTime date1 = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
    private static LocalDateTime date2 = LocalDateTime.of(2023, 12, 10, 0, 0, 0);

    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 유료강의_최대인원() {
        Session session = new Session(0L, 0L, date1, date2, null, PAID, 100, 100, 100000L, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionEnroll(session, student, payment));
    }

    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    @Test
    void 수강신청_성공_수강료() {
        Session session = new Session(0L, 0L, date1, date2, null, PAID, 100, 10, 100000L, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        SessionEnroll enrolled = new SessionEnroll(session, student, payment);

        Student enrolledStudent = enrolled.getStudent();
        assertThat(enrolledStudent).isEqualTo(student);
        assertThat(session.getAppliedNumber()).isEqualTo(11);
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @Test
    void 강의상태_모집중() {
        Session session = new Session(0L, 0L, date1, date2, null, PAID, 100, 10, 100000L, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        SessionEnroll enrolled = new SessionEnroll(session, student, payment);

        Student enrolledStudent = enrolled.getStudent();
        assertThat(enrolledStudent).isEqualTo(student);
    }

    @Test
    void 강의상태_불가() {
        Session session1 = new Session(0L, 0L, date1, date2, null, FREE, null, null, null, PREPARING);
        Session session2 = new Session(0L, 0L, date1, date2, null, FREE, null, null, null, CLOSED);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionEnroll(session1, student, payment));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionEnroll(session2, student, payment));
    }
}