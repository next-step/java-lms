package nextstep.courses.domain.registration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RegistrationsTest {

    @Test
    void 최대수강인원_초과하면_예외() {
        Registrations registrations = new Registrations(1);
        registrations = registrations.add(new Registration(1L, 1L));

        Registrations finalRegistrations = registrations;
        assertThatThrownBy(() -> finalRegistrations.add(new Registration(1L, 2L)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
    }

    @Test
    void 최대수강인원_이하면_등록_가능() {
        Registrations registrations = new Registrations(2);

        assertThatCode(() -> registrations.add(new Registration(1L, 1L)))
            .doesNotThrowAnyException();
    }

    @Test
    void 무제한이면_수강인원_제한없음() {
        Registrations registrations = new Registrations();

        for (long i = 0; i < 1000; i++) {
            registrations = registrations.add(new Registration(1L, i));
        }

        assertThat(registrations.count()).isEqualTo(1000);
    }
}
