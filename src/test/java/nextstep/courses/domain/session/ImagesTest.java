package nextstep.courses.domain.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImagesTest {
    @Test
    @DisplayName("이미지 목록을 생성한다")
    public void create() {
        Images images = Images.of(Image.from(), Image.from());
        Assertions.assertThat(images.size()).isEqualTo(2);
    }
}
