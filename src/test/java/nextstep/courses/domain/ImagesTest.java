package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ImagesTest {

    @Test
    void 생성_및_조회() {
        Image image1 = new Image(100, "jpg", "url1", 300, 200);
        Image image2 = new Image(100, "png", "url2", 300, 200);

        Images images = new Images(List.of(image1, image2));

        assertThat(images.getImages()).hasSize(2)
                .containsExactly(image1, image2);
    }

    @Test
    void 생성자에서_복사된_리스트는_외부_리스트_변경_영향을_받지_않는다() {
        List<Image> original = new ArrayList<>();
        original.add(new Image(100, "jpg", "url1", 300, 200));

        Images images = new Images(original);

        original.add(new Image(100, "png", "url2", 300, 200));

        assertThat(images.getImages()).hasSize(1);
    }

    @Test
    void getImages로_가져온_리스트는_수정할_수_없다() {
        Images images = new Images(List.of(new Image(100, "jpg", "url1", 300, 200)));

        List<Image> retrieved = images.getImages();

        assertThatThrownBy(() -> retrieved.add(new Image(100, "png", "url2", 300, 200)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}