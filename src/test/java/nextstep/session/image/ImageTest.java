package nextstep.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {

    @DisplayName("이미지를 생성한다.")
    @Test
    void createImageTest() {
        //given
        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        //when, then
        assertThat(image)
                .extracting("name", "size.width", "size.height", "capacity")
                .contains("테스트이미지.jpg", new ImageWidth(300), new ImageHeight(200), new ImageCapacity(1));
    }

}
