package nextstep.courses.domain.cover;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.error.exception.ImageHeightSizeException;
import nextstep.courses.error.exception.ImageSizeException;
import nextstep.courses.error.exception.ImageWidthSizeException;
import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    void 이미지의_크기가_1MB보다_클_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image(new ImageSize(2), ImageType.JPG, new ImageWidth(2), new ImageHeight(3)))
            .isInstanceOf(ImageSizeException.class)
            .hasMessage("이미지 사이즈의 최대 크기는 1입니다 입력값: 2");
    }

    @Test
    void width는_300픽셀_이상이_아닐_경우_예외가_발생해야_한다(){
        assertThatThrownBy(() -> new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(299),
            new ImageHeight(200)))
            .isInstanceOf(ImageWidthSizeException.class)
            .hasMessage("가로 사이즈는 300이상 이어야 합니다 입력값: 299");
    }

    @Test
    void height는_200픽셀_이상이_아닐_경우_예외가_발생해야_한다(){
        assertThatThrownBy(() -> new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300),
            new ImageHeight(199)))
            .isInstanceOf(ImageHeightSizeException.class)
            .hasMessage("높이 사이즈는 200이상이어야 합니다 입력값: 199");
    }

    @Test
    void width는_300픽셀_height는_200픽셀_이상이_아닐_경우_ImageWidthSizeException_예외가_발생해야_한다(){
        assertThatThrownBy(() -> new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(299),
            new ImageHeight(199)))
            .isInstanceOf(ImageWidthSizeException.class);
    }

    @Test
    void 이미지의_width는_300픽셀_height는_200픽셀_이상이어야한다() {
        Image image = new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(900),
            new ImageHeight(600));
        assertThat(image.getWidth()).isEqualTo(900);
        assertThat(image.getHeight()).isEqualTo(600);
    }

    @Test
    void width와_height의_비율이_3_2가_아닐_경우_예외가_발생한다() {
        Image image = new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300),
            new ImageHeight(201));
        assertThat(image.isThreeToTwoRatio()).isFalse();
    }

    @Test
    void width와_height의_비율이_3_2가_여야_한다() {
        Image image = new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300),
            new ImageHeight(200));
        assertThat(image.isThreeToTwoRatio()).isTrue();
    }
}
