package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class SessionStudentTest {

    @Test
    @DisplayName("SessionStudent_validate_test")
    public void SessionStudent_validate_test() {
        SessionStudent sessionStudent = new SessionStudent(30, 30);
        assertThatIllegalStateException()
                .isThrownBy(() -> {
                    sessionStudent.register();
                });
    }

    @Test
    @DisplayName("SessionStudent_register_test")
    public void SessionStudent_register_test() {
        SessionStudent sessionStudent = new SessionStudent(20, 30);
        sessionStudent.register();
        assertThat(sessionStudent.getNumberOfRegisteredStudent()).isEqualTo(21);
    }
}
