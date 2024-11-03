package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class TobeCoverImageTest {

    public static final int SIZE_1024 = 1024 * 1024;
    public static final int SIZE_512 = 512 * 1024;
    public static final String IMAGE_TYPE_TEXT_GIF = "gif";
    public static final String IMAGE_TYPE_TEXT_JPG = "gif";
    public static final ImageType IMAGE_TYPE_GIF = ImageType.toImageType(IMAGE_TYPE_TEXT_GIF);
    public static final ImageType IMAGE_TYPE_JPG = ImageType.toImageType(IMAGE_TYPE_TEXT_JPG);
    public static final double WIDTH_300 = 300;
    public static final double WIDTH_450 = 450;
    public static final double HEIGHT_200 = 200;
    public static final double HEIGHT_300 = 300;

    public static final List<TobeCoverImage> TOBE_COVER_IMAGE_LIST1 = List.of(
            new TobeCoverImage(1L, 1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, START, START),
            new TobeCoverImage(2L, 1L, SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300, START, START)
    );

    @Test
    void create() {
        TobeCoverImage actual0 = new TobeCoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        TobeCoverImage expected0 = new TobeCoverImage(1L, new ImageFileSize(SIZE_1024), IMAGE_TYPE_GIF, new ImageSize(WIDTH_300, HEIGHT_200));
        TobeCoverImage actual1 = new TobeCoverImage(0L, 1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, START, START);
        TobeCoverImage expected1 = new TobeCoverImage(0L, 1L, new ImageFileSize(SIZE_1024), IMAGE_TYPE_GIF, new ImageSize(WIDTH_300, HEIGHT_200), START, START);

        Assertions.assertThat(actual0).isEqualTo(expected0);
        Assertions.assertThat(actual0).isEqualTo(expected1);
        Assertions.assertThat(actual1).isEqualTo(expected0);
        Assertions.assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void getter() {
        TobeCoverImage coverImage = new TobeCoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        long actualId = coverImage.getId();
        ImageFileSize actualImageFileSize = coverImage.getImageFileSize();
        ImageSize actualImageSize = coverImage.getImageSize();
        ImageType actualImageType = coverImage.getImageType();

        assertThat(actualId).isEqualTo(0L);
        assertThat(actualImageFileSize).isEqualTo(new ImageFileSize(SIZE_1024));
        assertThat(actualImageSize).isEqualTo(new ImageSize(WIDTH_300, HEIGHT_200));
        assertThat(actualImageType).isEqualTo(ImageType.gif);
    }
}
