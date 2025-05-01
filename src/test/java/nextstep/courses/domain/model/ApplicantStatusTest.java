package nextstep.courses.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ApplicantStatusTest {

    @ParameterizedTest
    @EnumSource(ApplicantStatus.class)
    @DisplayName("모든 열거형 상수 값에 대해 변환가능 여부를 확인할 수 있다")
    void canChangeTo(ApplicantStatus status) {
        assertFalse(status.canChangeTo(ApplicantStatus.NOT_APPLIED));
    }

}
