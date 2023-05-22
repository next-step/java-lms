package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class EnrollmentTest {
    @Test
    void 수강신청_생성() {
        Enrollment dut = new Enrollment(10, 4, SessionStatus.ENROLLING);
        assertThat(dut).isEqualTo(new Enrollment(10, 4, SessionStatus.ENROLLING));
    }

    @Test
    void 수강신청_성공() {
        Enrollment before = new Enrollment(10, 9, SessionStatus.ENROLLING);
        Enrollment after = before.enroll();
        assertThat(after).isEqualTo(new Enrollment(10, 10, SessionStatus.ENROLLING));
    }

    @Test
    void 수강인원_초과() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(10, 11, SessionStatus.ENROLLING));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(10, 10, SessionStatus.ENROLLING).enroll());
    }

    @Test
    void 수강신청_상태가_아닐_때_수강신청() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Enrollment(10, 9, SessionStatus.PREPARING).enroll());
    }
}
