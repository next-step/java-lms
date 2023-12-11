package nextstep.courses.domain.coverimage;

import nextstep.courses.InvalidImageDimensionException;
import nextstep.courses.MaxImageSizeExceededException;
import nextstep.courses.UnsupportedImageTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    @Test
    @DisplayName("이미지 크기가 1MB 초과 시 에러 발생")
    public void 이미지_크기_초과() {
        assertThatThrownBy(() -> {
            new CoverImage(1025, "jpg", 300, 200);
        }).isInstanceOf(MaxImageSizeExceededException.class);
    }

    @Test
    public void 이미지_크기_정상() {
        Assertions.assertThatCode(() -> {
            new CoverImage(1023, "jpg", 300, 200);
        }).doesNotThrowAnyException();
    }

    @Test
    public void 이미지_타입_비허용() {
        assertThatThrownBy(() -> {
            new CoverImage(50, "csv", 300, 200);
        }).isInstanceOf(UnsupportedImageTypeException.class);
    }

    @ParameterizedTest
    @DisplayName("이미지 너비 300픽셀, 높이 200 픽셀 미만인 경우 에러 발생")
    @CsvSource({
            "250, 200",
            "300, 150"
    })
    public void 이미지_너비_높이_미만(int width, int height) {
        assertThatThrownBy(() -> {
            new CoverImage(50, "jpg", width, height);
        }).isInstanceOf(InvalidImageDimensionException.class);
    }

    @ParameterizedTest
    @DisplayName("이미지 비율 3:2가 아닐 경우 에러 발생")
    @CsvSource({
            "300, 300",
            "400, 600"
    })
    public void 이미지_비율_비허용(int width, int height) {
        assertThatThrownBy(() -> {
            new CoverImage(50, "jpg", width, height);
        }).isInstanceOf(InvalidImageDimensionException.class);
    }
}