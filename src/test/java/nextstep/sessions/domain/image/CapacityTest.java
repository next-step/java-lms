package nextstep.sessions.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CapacityTest {

    @DisplayName("capacity객체를 생성한다")
    @Test
    void capacity() {
        assertThat(new Capacity(10)).isEqualTo(new Capacity(10));
    }

    @DisplayName("capacity가 지정된 크기를 초과하면 예외를 반환한다")
    @Test
    void capacity_exception() {
        assertThatThrownBy(() ->
                new Capacity(2000 * 2000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이미지크기는 1MB(%s)를 초과할 수 없다", 1024 * 1024));
    }

}
