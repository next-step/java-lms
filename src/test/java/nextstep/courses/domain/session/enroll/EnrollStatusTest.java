package nextstep.courses.domain.session.enroll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.enroll.EnrollStatus.*;
import static org.assertj.core.api.Assertions.*;

class EnrollStatusTest {

    @DisplayName("인자로 EnrollStatus를 받아 비모집중인지 확인한다.")
    @Test
    void isEnrollOff() {
        assertThat(EnrollStatus.isEnrollOff(ENROLL_OFF)).isTrue();
    }
}