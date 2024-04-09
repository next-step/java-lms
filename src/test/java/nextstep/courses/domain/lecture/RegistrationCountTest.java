package nextstep.courses.domain.lecture;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RegistrationCountTest {

    @Test
    void 등록수는_0일수_없다() {
        RegistrationCount registrationCount1 = new RegistrationCount(0);
        RegistrationCount registrationCount2 = new RegistrationCount(5);

        assertThat(registrationCount1.isValueZero()).isFalse();
        assertThat(registrationCount2.isValueZero()).isTrue();
    }

    @Test
    void 등록수_조회가_가능해야한다() {
        RegistrationCount registrationCount = new RegistrationCount(10);

        assertThat(registrationCount.getValue()).isEqualTo(10);
    }
}
