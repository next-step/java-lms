package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnrollmentManagerTest {

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void free_session_has_no_limit() {
        assertTrue(new EnrollmentManager(0L, 0).canEnroll(new Payment()));
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void paid_lecture_cannot_exceed_limit_number() {
        Payment payment = new Payment("1234", 1L, 1L, 200L);
        assertFalse(new EnrollmentManager(200L, 0).canEnroll(payment));
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void paid_lecture_can_enroll_when_match_fee() {
        Payment payment = new Payment("1234", 1L, 1L, 200L);
        assertTrue(new EnrollmentManager(200L, 1).canEnroll(payment));
        assertFalse(new EnrollmentManager(201L, 1).canEnroll(payment));
        assertFalse(new EnrollmentManager(199L, 1).canEnroll(payment));
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void can_enroll_when_recruiting() {
        Payment payment = new Payment("1234", 1L, 1L, 200L);
        assertTrue(new EnrollmentManager(200L, 1, SessionStatus.RECRUITING).canEnroll(payment));
        assertFalse(new EnrollmentManager(200L, 1, SessionStatus.CLOSED).canEnroll(payment));
        assertFalse(new EnrollmentManager(200L, 1, SessionStatus.PREPARING).canEnroll(payment));
    }
}
