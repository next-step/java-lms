package nextstep.session.domain.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoverImagesTest {

    private CoverImage image1;
    private CoverImage image2;
    private CoverImages coverImages;

    @BeforeEach
    void setUp() {
        image1 = new CoverImage.Builder()
                .id(1L)
                .fileName("cover1.jpg")
                .fileSize(1024)
                .imageFormat("jpg")
                .imageSize(300, 200)
                .sessionId(1L)
                .build();

        image2 = new CoverImage.Builder()
                .id(2L)
                .fileName("cover2.png")
                .fileSize(2048)
                .imageFormat("png")
                .imageSize(600, 400)
                .sessionId(1L)
                .build();

        List<CoverImage> imageList = new ArrayList<>();
        imageList.add(image1);

        coverImages = new CoverImages(imageList);
    }

    @Test
    void 생성자_호출시_초기값_정상_확인() {
        assertThat(coverImages.size()).isEqualTo(1);
        assertThat(coverImages.get(0)).isEqualTo(image1);
    }

    @Test
    void add_호출시_리스트에_정상_추가된다() {
        coverImages.add(image2);
        assertThat(coverImages.size()).isEqualTo(2);
        assertThat(coverImages.get(1)).isEqualTo(image2);
    }

    @Test
    void get_호출시_정확한_인덱스의_이미지를_반환한다() {
        CoverImage result = coverImages.get(0);
        assertThat(result.getFileName()).isEqualTo("cover1.jpg");
    }
}
