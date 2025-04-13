package nextstep.courses.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageFileNameTest {

    @Test
    @DisplayName("정상적인 파일명을 생성한다")
    void create() {
        // given
        String fullFileName = "test.jpg";

        // when
        ImageFileName imageFileName = new ImageFileName(fullFileName);

        // then
        assertThat(imageFileName).isNotNull();
        assertThat(imageFileName.getName()).isEqualTo("test");
        assertThat(imageFileName.getExtension()).isEqualTo(ImageExtension.JPG);
        assertThat(imageFileName.getFullFileName()).isEqualTo(fullFileName);
    }

    @Test
    @DisplayName("대소문자 구분 없이 파일명을 생성할 수 있다")
    void createCaseInsensitive() {
        // given
        String fullFileName = "TEST.JPG";

        // when
        ImageFileName imageFileName = new ImageFileName(fullFileName);

        // then
        assertThat(imageFileName).isNotNull();
        assertThat(imageFileName.getName()).isEqualTo("TEST");
        assertThat(imageFileName.getExtension()).isEqualTo(ImageExtension.JPG);
        assertThat(imageFileName.getFullFileName()).isEqualTo("TEST.jpg");
    }

    @Test
    @DisplayName("올바르지 않은 파일명 형식이면 예외가 발생한다")
    void validateFileNameFormat() {
        // given
        String fullFileName = "test"; // 확장자 없음

        // when & then
        assertThatThrownBy(() -> new ImageFileName(fullFileName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 파일명 형식이 아닙니다.");
    }

    @Test
    @DisplayName("허용되지 않는 확장자로 파일명을 생성하면 예외가 발생한다")
    void validateExtension() {
        // given
        String fullFileName = "test.txt";

        // when & then
        assertThatThrownBy(() -> new ImageFileName(fullFileName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 파일 확장자입니다.");
    }
} 