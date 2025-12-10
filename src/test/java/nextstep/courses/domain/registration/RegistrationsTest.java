package nextstep.courses.domain.registration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RegistrationsTest {

    @Test
    void 중복_등록하면_예외() {
      Registrations registrations = new Registrations();
        registrations = registrations.add(new Registration(1L, 1L));

        Registrations finalRegistrations = registrations;
      assertThatThrownBy(() -> finalRegistrations.add(new Registration(1L, 1L)))
            .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("이미 수강신청한 학생입니다.");
    }

    @Test
    void 새로운_학생_등록_가능() {
      Registrations registrations = new Registrations();

      Registrations updated = registrations.add(new Registration(1L, 1L));

      assertThat(updated.count()).isEqualTo(1);
    }

    @Test
    void 여러_학생_등록_가능() {
        Registrations registrations = new Registrations();

        for (long i = 0; i < 1000; i++) {
            registrations = registrations.add(new Registration(1L, i));
        }

        assertThat(registrations.count()).isEqualTo(1000);
    }
}
