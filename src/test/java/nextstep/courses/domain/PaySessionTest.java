package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaySessionTest {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";
    private static final String PAYMENT_AMOUNT_WRONG = "수강료가 일치하지 않습니다.";
    private static final String NUMBER_OF_STUDENTS_IS_FULL = "해당 강의는 정원 마감입니다.";

    private Payment payment;
    private PaySession paySession;
    @BeforeEach
    void setUp() {
        payment = new Payment("1", 123L, 1L, 0L);
        paySession = PaySession.builder()
                .sessionDate(SessionDate.of(LocalDateTime.now(), LocalDateTime.now()))
                .sessionStatus(SessionStatus.READY)
                .price(2000L)
                .maxNumberOfStudents(2)
                .build();
    }

    @Test
    @DisplayName("수강 신청 시 status가 모집 중이 아니면 예외 발생")
    void enroll_status_exception() {
        assertThatThrownBy(() -> paySession.enroll(payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(SESSION_NOT_RECRUITING);
    }

    @Test
    @DisplayName("payment의 결제금액이 수강료가 일치하지 않는 경우 예외 발생")
    void enroll_wrong_price_exception() {
        paySession.startRecruit();
        Payment wrongPricePayment = new Payment("test", 12L, 12L, 1000L);

        assertThatThrownBy(() -> paySession.enroll(wrongPricePayment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(PAYMENT_AMOUNT_WRONG);
    }

    @Test
    @DisplayName("현재 수강 신청 인원이 최대 수강 신청 인원과 같으면 예외 발생")
    void enroll_students_full_exception() {
        //given
        paySession.startRecruit();

        paySession.enroll(new Payment("test1", 1L, 1L, 2000L));
        paySession.enroll(new Payment("test2", 2L, 2L, 2000L));

        //when
        assertThatThrownBy(() -> paySession.enroll(new Payment("test3", 3L, 3L, 2000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_OF_STUDENTS_IS_FULL);
    }
}
