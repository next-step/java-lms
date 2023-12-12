package nextstep.courses.domain.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionEnrollmentTest {
    @Test
    @DisplayName("SessionEnrollment 생성")
    void create() {
        assertThat(new SessionEnrollment()).isInstanceOf(SessionEnrollment.class);
    }
}
