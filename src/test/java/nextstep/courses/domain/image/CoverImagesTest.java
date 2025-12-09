package nextstep.courses.domain.image;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class CoverImagesTest {

    @Test
    void 이미지_등록(){
        CoverImages coverImages = new CoverImages();
        CoverImage coverImage = new CoverImage(1L, "png", 300, 200);
        coverImages.add(coverImage);
        Assertions.assertThat(coverImages.size()).isEqualTo(1);
    }

}