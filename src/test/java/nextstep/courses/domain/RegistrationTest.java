package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class RegistrationTest {
    @Test
    public void register_NotOpen() {
        Registration registration = new Registration(RegistrationStatus.CLOSED, 30L);
        assertThatIllegalStateException().isThrownBy(() -> registration.register(NsUserTest.JAVAJIGI));
    }

    @Test
    public void register_Full() {
        Registration registration = new Registration(RegistrationStatus.OPEN, 2L);
        registration.register(NsUserTest.JAVAJIGI);
        registration.register(NsUserTest.SANJIGI);
        registration.approve(NsUserTest.JAVAJIGI);
        registration.approve(NsUserTest.SANJIGI);
        assertThatIllegalStateException().isThrownBy(() -> registration.register(NsUserTest.YESJIGI));
    }

    @Test
    public void register_Approved() {
        Registration registration = new Registration(RegistrationStatus.OPEN, 2L);
        registration.register(NsUserTest.JAVAJIGI);
        registration.approve(NsUserTest.JAVAJIGI);
        assertThatIllegalStateException().isThrownBy(() -> registration.register(NsUserTest.JAVAJIGI));
    }

    @Test
    public void approve_Full() {
        Registration registration = new Registration(RegistrationStatus.OPEN, 2L);
        registration.register(NsUserTest.JAVAJIGI);
        registration.register(NsUserTest.SANJIGI);
        registration.register(NsUserTest.YESJIGI);
        registration.approve(NsUserTest.JAVAJIGI);
        registration.approve(NsUserTest.SANJIGI);
        assertThatIllegalStateException().isThrownBy(() -> registration.approve(NsUserTest.YESJIGI));
    }
}
