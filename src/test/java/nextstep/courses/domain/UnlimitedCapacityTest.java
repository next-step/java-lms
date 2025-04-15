package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UnlimitedCapacityTest {
    @Test
    void 무료_남은자리_항상_true반환() {
        assertThat(CapacityFactory.forFree().hasRoom()).isTrue();
    }

    @Test
    @DisplayName("")
    void 현재인원을_1_증가시킨_새_Capacity_객체반환() {
        assertThat(CapacityFactory.forFree().increase()).isEqualTo(new UnlimitedCapacity(1));
    }

    @Test
    void 무료_남은_수용가능인원_항상_무한대() {
        assertThat(CapacityFactory.forFree().remaining()).isEqualTo(Integer.MAX_VALUE);
    }
}
