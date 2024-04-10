package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.session.RegistrationCount;
import org.junit.jupiter.api.Test;

class RegistrationCountTest {

    @Test
    void 등록수는_0일수_없다() {
        RegistrationCount registrationCount1 = new RegistrationCount(0);
        RegistrationCount registrationCount2 = new RegistrationCount(5);

        assertThat(registrationCount1.isValueZero()).isTrue();
        assertThat(registrationCount2.isValueZero()).isFalse();
    }

    @Test
    void 등록수_조회가_가능해야한다() {
        RegistrationCount registrationCount = new RegistrationCount(10);

        assertThat(registrationCount.getValue()).isEqualTo(10);
    }
}
