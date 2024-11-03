package nextstep.courses.domain;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverImageTest {
    public static final int SIZE_1024 = 1024 * 1024;
    public static final int SIZE_512 = 512 * 1024;
    public static final String IMAGE_TYPE_TEXT_GIF = "gif";
    public static final String IMAGE_TYPE_TEXT_JPG = "jpg";
    public static final ImageType IMAGE_TYPE_GIF = ImageType.toImageType(IMAGE_TYPE_TEXT_GIF);
    public static final ImageType IMAGE_TYPE_JPG = ImageType.toImageType(IMAGE_TYPE_TEXT_JPG);
    public static final double WIDTH_300 = 300;
    public static final double WIDTH_450 = 450;
    public static final double HEIGHT_200 = 200;
    public static final double HEIGHT_300 = 300;

    public static final List<CoverImage> COVER_IMAGE_LIST1 = List.of(
            new CoverImage(1L, 1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, LocalDateTime.of(2024, 10, 26, 10, 0), LocalDateTime.of(2024, 11, 26, 10, 0)),
            new CoverImage(2L, 1L, SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300,LocalDateTime.of(2024, 10, 26, 10, 0), LocalDateTime.of(2024, 11, 26, 10, 0))
    );

    @Test
    void create() {
        CoverImage actual0 = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        CoverImage expected0 = new CoverImage(1L, new ImageFileSize(SIZE_1024), IMAGE_TYPE_GIF, new ImageSize(WIDTH_300, HEIGHT_200));
        CoverImage actual1 = new CoverImage(0L, 1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, START, START);
        CoverImage expected1 = new CoverImage(0L, 1L, new ImageFileSize(SIZE_1024), IMAGE_TYPE_GIF, new ImageSize(WIDTH_300, HEIGHT_200), START, START);

        Assertions.assertThat(actual0).isEqualTo(expected0);
        Assertions.assertThat(actual0).isEqualTo(expected1);
        Assertions.assertThat(actual1).isEqualTo(expected0);
        Assertions.assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void getter() {
        CoverImage coverImage = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
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