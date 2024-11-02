package nextstep.courses.tobe.domain.session;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class TobeCoverImageTest {

    public static final int SIZE = 1024 * 1024;
    public static final String IMAGE_TYPE_TEXT = "gif";
    public static final ImageType IMAGE_TYPE = ImageType.toImageType(IMAGE_TYPE_TEXT);
    public static final double WIDTH = 300;
    public static final double HEIGHT = 200;

    @Test
    void create() {
        TobeCoverImage actual0 = new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L);
        TobeCoverImage expected0 = new TobeCoverImage(new ImageFileSize(SIZE), IMAGE_TYPE, new ImageSize(WIDTH, HEIGHT), 1L);
        TobeCoverImage actual1 = new TobeCoverImage(0L, SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L, START);
        TobeCoverImage expected1 = new TobeCoverImage(0L, new ImageFileSize(SIZE), IMAGE_TYPE, new ImageSize(WIDTH, HEIGHT), 1L, START);

        Assertions.assertThat(actual0).isEqualTo(expected0);
        Assertions.assertThat(actual0).isEqualTo(expected1);
        Assertions.assertThat(actual1).isEqualTo(expected0);
        Assertions.assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void getter() {
        TobeCoverImage coverImage = new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT,1L);
        long actualId = coverImage.getId();
        ImageFileSize actualImageFileSize = coverImage.getImageFileSize();
        ImageSize actualImageSize = coverImage.getImageSize();
        ImageType actualImageType = coverImage.getImageType();

        assertThat(actualId).isEqualTo(0L);
        assertThat(actualImageFileSize).isEqualTo(new ImageFileSize(SIZE));
        assertThat(actualImageSize).isEqualTo(new ImageSize(WIDTH, HEIGHT));
        assertThat(actualImageType).isEqualTo(ImageType.gif);
    }
}
