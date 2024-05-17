package nextstep.courses.domain;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ImageTest {

    @Test
    public void 이미지생성() {
        Image image = new Image(1020, ImageType.GIT, 300, 200);

        assertThatThrownBy(() -> {
            new Image(100000000, ImageType.GIT, 300, 200);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("이미지 파일 크기가 너무 큽니다");

        assertThatThrownBy(() -> {
            new Image(1020, null, 300, 200);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("잘못된 파일 타입입니다");

        assertThatThrownBy(() -> {
            new Image(1020, ImageType.GIT, 100, 200);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("크기가 맞지 않습니다");

        assertThat(image).isEqualTo(new Image(1020, ImageType.GIT, 300, 200));
    }
}