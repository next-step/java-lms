package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EnrollmentCountTest {

    public static final EnrollmentCount ENROLLMENT_COUNT = new EnrollmentCount(0);

    @Test
    @DisplayName("새로운 수강 신청 인원 수를 생성한다.")
    void EnrollmentCount() {
        assertThat(new EnrollmentCount(0))
                .isEqualTo(ENROLLMENT_COUNT);
    }

    @Test
    @DisplayName("수강 신청 인원 수가 0명 미만인 경우 예외를 던진다.")
    void NegativeEnrollmentCount_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new EnrollmentCount(-1));
    }
}
