package nextstep.courses.domain.session.policy.capacity;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;
import org.junit.jupiter.api.Test;

class LimitedCapacityTest {

    @Test
    void 현재인원이_최대수강인원을_초과하면_예외() {
        LimitedCapacity capacity = new LimitedCapacity(2);
        Registrations registrations = new Registrations()
            .add(new Registration(1L, 1L))
            .add(new Registration(1L, 2L))
            .add(new Registration(1L, 3L));

        assertThatThrownBy(() -> capacity.validate(registrations))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("최대 수강 인원을 초과할 수 없습니다.");
    }

    @Test
    void 현재인원이_최대수강인원과_같으면_검증_통과() {
        LimitedCapacity capacity = new LimitedCapacity(2);
        Registrations registrations = new Registrations()
            .add(new Registration(1L, 1L))
            .add(new Registration(1L, 2L));

        assertThatCode(() -> capacity.validate(registrations))
            .doesNotThrowAnyException();
    }

    @Test
    void 현재인원이_최대수강인원_미만이면_검증_통과() {
        LimitedCapacity capacity = new LimitedCapacity(3);
        Registrations registrations = new Registrations()
            .add(new Registration(1L, 1L));

        assertThatCode(() -> capacity.validate(registrations))
            .doesNotThrowAnyException();
    }
}