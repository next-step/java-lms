package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    public static Session createSession(SessionStatus status) {
        return Session.createFreeSession(LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, status);
    }

    public static Session createSession(Long price, int capacity) {
        return Session.createPaidSession(LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, SessionStatus.OPEN, price, capacity);
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void createFreeSession() {
        assertThatCode(() -> createSession(0L, Integer.MAX_VALUE).enroll(new Student())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때 가능하다.")
    void registerOpenSession() {
        assertThatCode(() -> createSession(SessionStatus.OPEN).enroll(new Student())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중이 아니면 불가능하다.")
    void registerNonOpenSession() {

        for (SessionStatus status : SessionStatus.values()) {

            if (status == SessionStatus.OPEN) continue;

            assertThatThrownBy(() -> createSession(status).enroll(new Student()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("session is not open");
        }
    }

    @Test
    @DisplayName("결제 정보는 Payment 객체에 담겨 반한된다.")
    void enrollAndGetPayment() {
        Session session = createSession(800_000L, 1);
        Student student = new Student(800_000L);
        assertThat(session.enroll(student).getAmount()).isEqualTo(800_000L);
    }

}
