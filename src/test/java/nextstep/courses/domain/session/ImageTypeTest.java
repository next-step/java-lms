package nextstep.courses.domain.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ImageTypeTest {

    @ParameterizedTest(name = "지정된 이미지 타입이 아니면 에러가 발생합니다.")
    @ValueSource(strings = {"Giif, jpp, ppng, ssvg"})
    void illegalType(String type) {
        // given
        // when
        // then
        Assertions.assertThatThrownBy(() -> ImageType.findType(type))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 데이터 타입입니다.");
    }
}