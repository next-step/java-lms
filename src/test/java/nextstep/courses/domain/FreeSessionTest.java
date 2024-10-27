package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FreeSessionTest {
    @Test
    void create() {
        Assertions.assertThatNoException().isThrownBy(() -> {
            FreeSession freeSession = new FreeSession();
            FreeSession addedStudentsFreeSession = new FreeSession(NsUserTest.JAVAJIGI);
        });
    }

    @Test
    void register_성공() {
        FreeSession freeSession = new FreeSession();
        freeSession.register(NsUserTest.JAVAJIGI);
        FreeSession expected = new FreeSession(NsUserTest.JAVAJIGI);

        Assertions.assertThat(freeSession).isEqualTo(expected);
    }
}