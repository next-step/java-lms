package nextstep.courses.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoverImageTest {

    @DisplayName("이미지 크기는 1mb 이하여야 한다")
    @Test
    void imageCapacityTest() {

        assertThatThrownBy(() -> new Capacity(10))
                .isInstanceOf(IllegalArgumentException.class);

    }
    
    @DisplayName("타입은 gif, jpg, png, svg만 허용한다")
    @Test
    void imageExtensionTest() {
        assertThatThrownBy(() -> new ImageFile("image.txt"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @DisplayName("width는 300픽셀, height는 200픽셀 이상")
    @Test
    void imageSizeTest() {

        assertThatThrownBy(() -> new ImageSize(299, 199))
                .isInstanceOf(IllegalArgumentException.class);
        
    }

    
    @DisplayName("이미지 비율은 3 : 2 여야 한다")
    @Test
    void imageProportionTest() {
        assertThatCode(() -> new ImageSize(600, 400))
                .doesNotThrowAnyException();
    }
}