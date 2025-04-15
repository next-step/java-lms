package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LimitedCapacityTest {
    @Test
    void 최대인원이_0이하일_경우_예외발생() {
        assertThatThrownBy(() -> CapacityFactory.forPaid(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 인원은 1명 이상이어야 합니다.");
    }

    @Test
    void 현재인원이_최대인원을_초과할_경우_예외발생() {
        assertThatThrownBy(() -> new LimitedCapacity(10, 11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 인원은 0 이상 최대 인원 이하이어야 합니다.");
    }

    @Test
    void 현재인원이_0이하일_경우_예외발생() {
        assertThatThrownBy(() -> new LimitedCapacity(10, 11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 인원은 0 이상 최대 인원 이하이어야 합니다.");
    }

    @Test
    void 정원이_남아있으면_수용가능여부_true반환() {
        assertThat(new LimitedCapacity(10, 5).hasRoom()).isTrue();
    }

    @Test
    void 정원이_가득찼으면_수용가능여부_false반환() {
        Capacity capacity = CapacityFactory.forPaid(1);
        assertThat(capacity.increase().hasRoom()).isFalse();
    }

    @Test
    @DisplayName("")
    void 현재인원을_1_증가시킨_새_Capacity_객체를반환() {
        Capacity capacity = CapacityFactory.forPaid(2);
        assertThat(capacity.increase()).isEqualTo(new LimitedCapacity(2, 1));
    }

    @Test
    void 정원이_가득_찬_상태에서_증가하면_예외발생() {
        Capacity capacity = CapacityFactory.forPaid(1);

        assertThatThrownBy(() -> capacity.increase().increase())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 남은_수용가능인원을_반환() {
        Capacity capacity = CapacityFactory.forPaid(2);
        assertThat(capacity.increase().remaining()).isEqualTo(1);
    }
}
