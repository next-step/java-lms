package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class RegistrationTest {
    @Test
    public void register_NotOpen() {
        Registration registration = new Registration(RegistrationStatus.CLOSED, new Students(), 30L);
        assertThatIllegalStateException().isThrownBy(() -> registration.register(NsUserTest.JAVAJIGI));
    }

    @Test
    public void register_Full() {
        Registration registration = new Registration(RegistrationStatus.OPEN, new Students(), 2L);
        registration.register(NsUserTest.JAVAJIGI);
        registration.register(NsUserTest.SANJIGI);
        assertThatIllegalStateException().isThrownBy(() -> registration.register(NsUserTest.YESJIGI));
    }
}
