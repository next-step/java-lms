package nextstep.courses.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageNameTest {

    @DisplayName("이미지 이름 생성")
    @Test
    void 이미지이름생성() {
        // given
        String name = "image.png";
        // when
        ImageName imageName = new ImageName(name);
        // then
        assertThat(imageName).isEqualTo(new ImageName(name));
    }

    @DisplayName("이미지 이름 생성시 확장자가 없으면 예외가 발생한다.")
    @Test
    void 이미지이름생성시_확장자가_없으면_예외가_발생한다() {
        // given
        String name = "image";
        // when
        // then
        assertThatThrownBy(() -> new ImageName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지 확장자가 지정된 확장자가 아니면 예외가 발생한다.")
    @Test
    void 이미지확장자가_지정된_확장자가_아니면_예외가_발생한다() {
        // given
        String name = "image.mp4";
        // when
        // then
        assertThatThrownBy(() -> new ImageName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
