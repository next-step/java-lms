package nextstep.courses.domain;

import nextstep.courses.domain.EnrollmentManager;
import nextstep.payments.domain.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

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

    @ParameterizedTest
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    @CsvSource(value = {"200:true", "201:false", "201:false"}, delimiter = ':') // 우선 금액이 더 커도 false 리턴하도록
    void paid_lecture_can_enroll_when_match_fee(Long fee, boolean result) {
        boolean canEnroll = new EnrollmentManager(200L, 1).canEnroll(new Payment("1234", 1L, 1L, fee));
        Assertions.assertThat(canEnroll).isEqualTo(result);
    }
}
