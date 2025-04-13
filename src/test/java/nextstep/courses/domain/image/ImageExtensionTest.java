package nextstep.courses.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageExtensionTest {

    @Test
    @DisplayName("정상적인 이미지 확장자를 생성한다")
    void create() {
        // given
        String extension = "jpg";

        // when
        ImageExtension imageExtension = ImageExtension.from(extension);

        // then
        assertThat(imageExtension).isNotNull();
        assertThat(imageExtension.getExtension()).isEqualTo(extension);
    }

    @ParameterizedTest
    @ValueSource(strings = {"txt", "doc", "exe"})
    @DisplayName("허용되지 않는 확장자로 생성하면 예외가 발생한다")
    void validateExtension(String extension) {
        // when & then
        assertThatThrownBy(() -> ImageExtension.from(extension))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 파일 확장자입니다.");
    }

    @Test
    @DisplayName("대소문자 구분 없이 확장자를 생성할 수 있다")
    void createCaseInsensitive() {
        // given
        String extension = "JPG";

        // when
        ImageExtension imageExtension = ImageExtension.from(extension);

        // then
        assertThat(imageExtension).isNotNull();
        assertThat(imageExtension.getExtension()).isEqualTo("jpg");
    }
} 