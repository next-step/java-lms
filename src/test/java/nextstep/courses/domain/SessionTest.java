package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    @Test
    @DisplayName("강의는 기수, 시작일, 종료일, 커버 이미지, 수강료, 강의상태, 최대 수강 인원을 가진다.")
    void createSession() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        assertThatCode(() -> new Session(1, start, end, "image.jpg", 800_000L, 100)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void createFreeSession() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(1, start, end, "image.jpg", 0L, 0, SessionStatus.OPEN);
        assertThatCode(() -> session.enroll(new Student())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void createPaidSession() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(1, start, end, "image.jpg", 800_000L, 1, SessionStatus.OPEN);
        session.enroll(new Student(800_000L));

        assertThatThrownBy(() -> session.enroll(new Student()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("student limit exceeded");
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void createPaidSessionWithCorrectPrice() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(1, start, end, "image.jpg", 800_000L, 1, SessionStatus.OPEN);
        assertThatCode(() -> session.enroll(new Student(800_000L))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치하지 않으면 수강 신청이 불가능하다.")
    void createPaidSessionWithNotEnoughPrice() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(1, start, end, "image.jpg", 800_000L, 1, SessionStatus.OPEN);
        assertThatThrownBy(() -> session.enroll(new Student(790_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not enough money");
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void createSessionWithStatus() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Student student = new Student();
        Session ready = new Session(1, start, end, "image.jpg", 0L, 1, SessionStatus.READY);
        assertThatThrownBy(() -> ready.enroll(student))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("session is not open");
        Session closed = new Session(1, start, end, "image.jpg", 0L, 1, SessionStatus.CLOSED);
        assertThatThrownBy(() -> closed.enroll(student))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("session is not open");
        Session open = new Session(1, start, end, "image.jpg", 0L, 1, SessionStatus.OPEN);
        assertThatCode(() -> open.enroll(student)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("결제 정보는 Payment 객체에 담겨 반한된다.")
    void enrollAndGetPayment() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(10L, 1, start, end, null, 800_000L, SessionStatus.OPEN, 1, new ArrayList<>());
        Student student = new Student(1L, 800_000L);
        Payment payment = session.enroll(student);
        assertThat(payment.getSessionId()).isEqualTo(10L);
        assertThat(payment.getNsUserId()).isEqualTo(1L);
        assertThat(payment.getAmount()).isEqualTo(800_000L);
    }

}
