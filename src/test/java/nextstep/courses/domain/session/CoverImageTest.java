package nextstep.courses.domain.session;

import nextstep.courses.dto.CoverImageDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CoverImageTest {

    @ParameterizedTest(name = "이미지 크기가 1MB 보다 크거나 0이하이면 에러를 발생시킵니다.")
    @ValueSource(ints = {1048577, 0})
    void overSize(int size) {
        // given
        CoverImageDto dto = new CoverImageDto("path", size, "gif", 300, 200);
        // when
        // then
        Assertions.assertThatThrownBy(() -> CoverImage.from(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하입니다.");
    }

    @ParameterizedTest(name = "특정 이미지 타입이 아니면 에러를 발생시킵니다.")
    @ValueSource(strings = {"Giif, jpp, ppng, ssvg"})
    void illegalType(String type) {
        // given
        CoverImageDto dto = new CoverImageDto("path", 100, type, 300, 200);
        // when
        // then
        Assertions.assertThatThrownBy(() -> CoverImage.from(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 데이터 타입입니다.");
    }

    @ParameterizedTest(name = "적절하지 않은 사이즈 크기이면 에러를 발생시킵니다.")
    @CsvSource({"100, 200", "300, 100", "300, 210"})
    void illegalSize(int width, int height) {
        // given
        CoverImageDto dto = new CoverImageDto("path", 100, "gif", width, height);
        // when
        // then
        Assertions.assertThatThrownBy(() -> CoverImage.from(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적절한 이미지 사이즈가 아닙니다.");
    }
}