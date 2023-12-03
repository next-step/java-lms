package nextstep.courses.domain.field;

import static nextstep.courses.domain.field.ImageType.GIF;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CoverImageTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "1048577",
            "1048578",
            "1048579",
            "1048580",
            "1048581",
            "1048582",
            "1048583",
            "1048584",
    })
    void 이미지_크기가_1MB_초과히는_경우_실패하는_테스트(long bytes) {
        assertThatThrownBy(() -> CoverImage.of(bytes,
                                                300L,
                                                200L,
                                               GIF.getExtension()))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "bad",
            "txt",
            "json",
            "sql",
            "kt",
            "java",
            "vue",
    })
    void 이미지_타입이_지원하지_않는_형식인_경우_실패하는_테스트(String extension) {
        assertThatThrownBy(() -> CoverImage.of(CoverImage.SIZE_LIMIT,
                                               300L,
                                               200L,
                                               extension))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "200, 300",
            "300, 300",
            "250, 500"
    })
    void 이미지_가로와_세로의_비율이_적절_하지_않은_경우_실패_하는_테스트(long width, long height) {
        assertThatThrownBy(() -> CoverImage.of(CoverImage.SIZE_LIMIT,
                                               width,
                                               height,
                                               GIF.getExtension()))
        .isInstanceOf(IllegalArgumentException.class);
    }
}