package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.domain.session.image.ImageSize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoverImageTest {

    public static final int SIZE = 1024 * 1024;
    public static final String IMAGE_TYPE_TEXT = "gif";
    public static final ImageType IMAGE_TYPE = ImageType.toImageType(IMAGE_TYPE_TEXT);
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    @Test
    void create() {
        CoverImage actual = new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT);
        CoverImage expected = new CoverImage(new ImageFileSize(SIZE), IMAGE_TYPE, new ImageSize(WIDTH, HEIGHT));

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
