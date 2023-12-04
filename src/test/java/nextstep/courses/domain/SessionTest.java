package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.SessionStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionTest {

    private static final LocalDateTime date1 = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
    private static final LocalDateTime date2 = LocalDateTime.of(2023, 12, 10, 0, 0, 0);

    @DisplayName("강의는 시작일과 종료일을 가지며, 시작일이 종료일 이후일 수 없다.")
    @Test
    void 강의_시작종료일() {
        Session session = Session.freeSession(0L, 0L, date1, date2, null,null, APPLYING);

        assertThat(session.getPeriod().getStartDate()).isEqualTo(date1);
        assertThat(session.getPeriod().getEndDate()).isEqualTo(date2);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Session.freeSession(0L, 0L, date2, date1, null,null, APPLYING));
    }

    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    @Test
    void 무료강의_인원제한() {
        Session freeSession = Session.freeSession(0L, 0L, date1, date2, null,null, APPLYING);

        assertThat(freeSession.getMaxStudentNumber()).isNull();
    }

    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 유료강의_최대인원() {
        Session session = Session.paidSession(0L, 0L, date1, date2, null, 100, 100, 100000L, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(student, payment));
    }

    @Test
    void 무료강의_수강신청() {
        Session session = Session.freeSession(0L, 0L, date1, date2, null, 10, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(0L);

        SessionEnroll enrolledFree = session.enroll(student, payment);
        Student enrolledStudent = enrolledFree.getStudent();

        assertThat(enrolledStudent).isEqualTo(student);
        assertThat(session.getAppliedNumber()).isEqualTo(11);
    }

    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    @Test
    void 수강신청_성공_수강료() {
        Session session = Session.paidSession(0L, 0L, date1, date2, null, 100, 10, 100000L, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        SessionEnroll enrolled = session.enroll(student, payment);
        Student enrolledStudent = enrolled.getStudent();

        assertThat(enrolledStudent).isEqualTo(student);
        assertThat(session.getAppliedNumber()).isEqualTo(11);
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @Test
    void 강의상태_모집중() {
        Session session = Session.paidSession(0L, 0L, date1, date2, null, 100, 10, 100000L, APPLYING);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(100000L);

        SessionEnroll enrolled = session.enroll(student, payment);
        Student enrolledStudent = enrolled.getStudent();

        assertThat(enrolledStudent).isEqualTo(student);
        assertThat(session.getAppliedNumber()).isEqualTo(11);
    }

    @Test
    void 강의상태_불가() {
        Session session1 = Session.freeSession(0L, 0L, date1, date2, null, null, PREPARING);
        Session session2 = Session.freeSession(0L, 0L, date1, date2, null, null, CLOSED);
        Student student = new Student(0L, "test");
        Payment payment = new Payment(0L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session1.enroll(student, payment));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session2.enroll(student, payment));
    }
}
