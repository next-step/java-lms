package nextstep.courses.domain.session.image;

import nextstep.courses.ImageTypeMismatchException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nextstep.courses.domain.session.image.ImageType.IMAGE_TYPE_MISMATCH_MESSAGE;

public class ImageTypeTest {
    @Test
    void toImageType() {
        ImageType gif = ImageType.toImageType("gif");
        ImageType jpg = ImageType.toImageType("jpg");
        ImageType jpeg = ImageType.toImageType("jpeg");
        ImageType png = ImageType.toImageType("png");
        ImageType svg = ImageType.toImageType("svg");

        Assertions.assertThat(gif).isEqualTo(ImageType.gif);
        Assertions.assertThat(jpg).isEqualTo(ImageType.jpg);
        Assertions.assertThat(jpeg).isEqualTo(ImageType.jpeg);
        Assertions.assertThat(png).isEqualTo(ImageType.png);
        Assertions.assertThat(svg).isEqualTo(ImageType.svg);
    }

    @ParameterizedTest(name = "toImageType_미스매치: {0}")
    @ValueSource(strings = {"exe", "pdf", "txt", "mp3"})
    void toImageType_미스매치(String type) {
        Assertions.assertThatThrownBy(() -> {
                    ImageType noneMatch = ImageType.toImageType(type);
                }).isInstanceOf(ImageTypeMismatchException.class)
                .hasMessage(IMAGE_TYPE_MISMATCH_MESSAGE);

    }
}
