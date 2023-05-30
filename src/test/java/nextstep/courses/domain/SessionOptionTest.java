package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionOptionTest {
    @Test
    @DisplayName("validate_test")
    public void validate_test(){
        assertThatIllegalStateException()
                .isThrownBy(() -> {
                    new SessionOption(SessionEnrollment.NON_ENROLLMENT).validateStatusOfSession();
                });
    }
}
