package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {

    @Test
    @DisplayName("이미지는 크기와 타입과 넓이와 높이를 가질 수 있다.")
    void 이미지는_크기와_타입과_넓이와_높이를_가질_수_있다() {
        Image image = new Image(1, "png", "300px", "200px");
        assertThat(image).hasFieldOrProperty("size");
        assertThat(image).hasFieldOrProperty("type");
        assertThat(image).hasFieldOrProperty("width");
        assertThat(image).hasFieldOrProperty("height");
    }
}
