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
}
