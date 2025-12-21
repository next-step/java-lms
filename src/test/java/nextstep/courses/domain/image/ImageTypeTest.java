package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageTypeTest {

    @Test
    void 이미지_타입을_추출해_반환한다() {
        assertThat(ImageType.extractType("파일이름.png"))
                .isEqualTo(ImageType.PNG);
    }

    @Test
    void 확장자가_없는_경우에_예외가_발생한다() {
        assertThatThrownBy(() -> ImageType.extractType("파일이름"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("확장자가 존재하지 않는 파일입니다.");
    }

    @Test
    void 지원하지_않는_이미지_타입의_파일이라면_예외가_발생한다() {
        assertThatThrownBy(() -> ImageType.extractType("파일이름.html"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("지원하지 않는 이미지 타입입니다.");
    }
}
