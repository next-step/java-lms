package nextstep.courses.domain.session.policy.capacity;

import static org.assertj.core.api.Assertions.assertThatCode;

import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import org.junit.jupiter.api.Test;

class UnlimitedCapacityTest {

    @Test
    void 무제한이면_수강인원_제한없음() {
        UnlimitedCapacity capacity = new UnlimitedCapacity();
        Registrations registrations = new Registrations();

        for (long i = 0; i < 1000; i++) {
            registrations = registrations.add(new Registration(1L, i));
            capacity.validate(registrations);
        }

      Registrations finalRegistrations = registrations;
      assertThatCode(() -> capacity.validate(finalRegistrations))
            .doesNotThrowAnyException();
    }
}