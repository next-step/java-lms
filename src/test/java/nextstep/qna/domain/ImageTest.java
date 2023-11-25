package nextstep.qna.domain;

import nextstep.courses.ImageSizeOverException;
import nextstep.courses.InvalidImageTypeException;
import nextstep.courses.domain.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImageTest {

    @Test
    @DisplayName("이미지 최대 크기를 초과할 경우 에러 발생한다")
    public void image_size_limit() {
        assertThatExceptionOfType(ImageSizeOverException.class)
            .isThrownBy(() -> new Image(2, "JPG"))
            .withMessageMatching("이미지 최대 크기를 초과했습니다.");
    }

    @Test
    @DisplayName("지원하지 않는 이미지 타입으로 이미지 생성 시 에러 발생한다")
    public void image_type() {
        assertThatExceptionOfType(InvalidImageTypeException.class)
            .isThrownBy(() -> new Image(2, "BMP"))
            .withMessageMatching("지원하지 않는 이미지 타입입니다.");
    }

}
