package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class FreeSessionTest {

    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";

    private Session freeSession;
    private Payment payment;

    @BeforeEach
    void setUp() {
        freeSession = FreeSession.of(SessionDate.of(LocalDateTime.now(), LocalDateTime.now()));
        payment = new Payment("1", 123L, 1L, 0L);
    }

    @Test
    @DisplayName("수강 신청 시 status가 모집 중이 아니면 예외 발생")
    void enrolment_status_exception() {
        freeSession.startSession();
        assertThatThrownBy(() -> freeSession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(SESSION_NOT_RECRUITING);
    }

    @Test
    @DisplayName("수강 신청 시 status가 모집 중이면 정상 신청, 현재 수강인원 1 증가")
    void enrolment() {
        //when
        freeSession.enroll(payment);

        //then
        assertThat(freeSession.hasNumberOfStudents(1)).isTrue();
    }
}
