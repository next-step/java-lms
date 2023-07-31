package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionUsersTest {

    @Test
    void validate() {
        SessionUsers sessionUsers = new SessionUsers(0);

        assertThatIllegalStateException().isThrownBy(() -> sessionUsers.enroll(new SessionUser(1L, 1L)));
    }
}
