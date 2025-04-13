package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionCapacityTest {

    @Test
    @DisplayName("등록하면 하나가 찬다.")
    void increase_success() {
        SessionCapacity capacity = new SessionCapacity(10);
        capacity.increase();
        assertEquals(capacity, new SessionCapacity(1, 10));
    }

    @Test
    @DisplayName("maxCapacity보다 더 수강하려면 에러를 반환한다.")
    void increase_fail() {
        SessionCapacity capacity = new SessionCapacity(10, 10);
        assertThatThrownBy(capacity::increase)
                .isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("초기화 시 수강인원이 최대 인원보다 크면 에러를 반환한다.")
    void init() {
        assertThatThrownBy(() -> new SessionCapacity(11, 10))
                .isInstanceOf(CannotEnrollException.class);
    }
}