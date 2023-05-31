package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CapacityTest {
    private final static String ADD_ERROR_MESSAGE = "최대 수용 인원을 초과하였습니다.";

    @Test
    @DisplayName("수강 인원 정상인 경우")
    void test01() {
        //given
        Capacity capacity = new Capacity(9, 10);

        //when
        capacity.add();

        //then
        Assertions.assertThat(capacity.getCurCapacity()).isEqualTo(10);

    }

    @Test
    @DisplayName("수강 인원 초과인 경우")
    void test02() {
        //given
        Capacity capacity = new Capacity(10, 10);

        // when & then
        Assertions.assertThatThrownBy(capacity::add)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ADD_ERROR_MESSAGE);

    }
}
