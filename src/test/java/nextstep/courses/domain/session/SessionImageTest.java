package nextstep.courses.domain.session;

import nextstep.courses.InvalidImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionImageTest {

    @Test
    @DisplayName("이미지의 width는 300 이상, height는 200이면, size는 1mb 보다 작으면 정상적으로 생성된다.")
    public void init() {
        SessionImage image = new SessionImage(300, 200, 10, "jpg");
        assertEquals(image, new SessionImage(300, 200, 10, "jpg"));
    }

    @Test
    @DisplayName("이미지의 최소 크기를 넘지 못하면 에러가 발생한다.")
    public void init_invalid_heightwidth() {
        assertThatThrownBy(() -> new SessionImage(30, 20, 10, "jgp"))
                .isInstanceOf(InvalidImageException.class);
    }

    @Test
    @DisplayName("이미지의 비율이 맞지 않으면 에러가 발생한다.")
    public void init_invalid_ratio() {
        assertThatThrownBy(() -> new SessionImage(20, 20, 10, "jpg"))
                .isInstanceOf(InvalidImageException.class);
    }

    @Test
    @DisplayName("이미지의 크기가 1mb보다 크면 에러가 발생한다.")
    public void init_invalid_size() {
        assertThatThrownBy(() -> new SessionImage(30, 20, 10000000, "jgp"))
                .isInstanceOf(InvalidImageException.class);
    }
}