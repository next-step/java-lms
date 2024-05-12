package nextstep.courses.domain.image;

import nextstep.courses.NotAcceptedImageTypeException;
import nextstep.courses.domain.Image.ImageType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageTypeTest {

    @Test
    void 이미지_타입_에러_테스트(){
        String type = "bmp";
        Assertions.assertThatThrownBy(()->ImageType.of(type))
            .isInstanceOf(NotAcceptedImageTypeException.class);
    }
}
