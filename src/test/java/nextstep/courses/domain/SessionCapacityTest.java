package nextstep.courses.domain;

import nextstep.users.domain.NsStudent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class SessionCapacityTest {

    public static final NsStudent JAVAJIGI = new NsStudent(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsStudent SANJIGI = new NsStudent(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    @DisplayName("SessionStudent_validate_test")
    public void SessionStudent_validate_test() {
        SessionCapacity sessionCapacity = new SessionCapacity(30, 30);
        assertThatIllegalStateException()
                .isThrownBy(() -> {
                    sessionCapacity.register();
                });
    }

    @Test
    @DisplayName("SessionStudent_register_test")
    public void SessionStudent_register_test() {
        SessionCapacity sessionCapacity = new SessionCapacity(20, 30);
        sessionCapacity.register();
        assertThat(sessionCapacity.getNumberOfRegisteredStudent()).isEqualTo(21);
    }
}
