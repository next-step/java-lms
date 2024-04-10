package nextstep.courses.domain.cover;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.error.ImageSizeException;
import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    void 이미지의_크기가_1MB보다_클_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image(new ImageSize(2), ImageType.JPG, 2, 3))
            .isInstanceOf(ImageSizeException.class)
            .hasMessage("이미지 사이즈의 최대 크기는 1입니다 입력값: 2");

    }

    @Test
    void width는_300픽셀_height는_200픽셀_이상이_아닐_경우_예외가_발생해야_한다() {
    }

    @Test
    void 이미지의_width는_300픽셀_height는_200픽셀_이어야한다() {
    }

    @Test
    void width와_height의_비율이_3_2가_아닐_경우_예외가_발생한다() {
    }

    @Test
    void width와_height의_비율이_3_2가_여야_한다() {
    }

    @Test
    void width와_height는_null_또는_빈_문자열일_수_없다() {
    }
}
