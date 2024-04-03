package nextstep.courses.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {

    @DisplayName("이미지 크기는 1mb 이하여야 한다")
    @Test
    void imageCapacityTest() {

        Capacity capacity = new Capacity(10);

        assertThatThrownBy(() -> new CoverImage(capacity))
                .isInstanceOf(IllegalArgumentException.class);

    }
    
    @DisplayName("타입은 gif, jpg, png, svg만 허용한다")
    @Test
    void imageExtensionTest() {
        ImageFile imageFile = new ImageFile("image.txt");
        assertThatThrownBy(() -> new CoverImage(imageFile))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @DisplayName("width는 300픽셀, height는 200픽셀 이상")
    @Test
    void imageSizeTest() {

        ImageSize imageSize = new ImageSize(299, 199);

        assertThatThrownBy(() -> new CoverImage(imageSize))
                .isInstanceOf(IllegalArgumentException.class);
        
    }

    
    @DisplayName("이미지 비율은 3 : 2 여야 한다")
    @Test
    void imageProportionTest() {
        ImageSize sut = new ImageSize(600, 400);
        assertThat(sut.satisfyProportion(3, 2)).isTrue();
    }
}