package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionEnrollmentTest {

    @Test
    @DisplayName("find_method_test")
    public void find_method_test() {
        assertThat(SessionEnrollment.find("ENROLLMENT")).isEqualTo(SessionEnrollment.ENROLLMENT);
        assertThat(SessionEnrollment.find("test")).isNull();
    }
}
