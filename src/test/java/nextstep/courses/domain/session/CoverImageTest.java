package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageTypeTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CoverImageTest {

    public static final int SIZE = 1024 * 1024;
    public static final String IMAGE_TYPE_TEXT = "gif";
    public static final ImageType IMAGE_TYPE = ImageType.toImageType(IMAGE_TYPE_TEXT);
    public static final double WIDTH = 300;
    public static final double HEIGHT = 200;

    @Test
    void create() {
        CoverImage actual = new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT);
        CoverImage expected = new CoverImage(new ImageFileSize(SIZE), IMAGE_TYPE, new ImageSize(WIDTH, HEIGHT));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getter() {
        CoverImage coverImage = new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT);
        ImageFileSize actualImageFileSize = coverImage.getImageFileSize();
        ImageSize actualImageSize = coverImage.getImageSize();
        ImageType actualImageType = coverImage.getImageType();

        assertThat(actualImageFileSize).isEqualTo(new ImageFileSize(SIZE));
        assertThat(actualImageSize).isEqualTo(new ImageSize(WIDTH, HEIGHT));
        assertThat(actualImageType).isEqualTo(ImageType.gif);
    }
}
