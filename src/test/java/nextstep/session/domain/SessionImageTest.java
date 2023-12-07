package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import static nextstep.session.domain.fixture.SessionImageFixture.DEFAULT_HEIGHT;
import static nextstep.session.domain.fixture.SessionImageFixture.DEFAULT_IMAGE_SIZE;
import static nextstep.session.domain.fixture.SessionImageFixture.DEFAULT_WIDTH;
import static nextstep.session.domain.fixture.SessionImageFixture.createSessionImage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionImageTest {

    @Test
    void 생성_성공() {
        // expect
        assertThat(SessionImage.of("test.gif", 1024 * 1024, 300, 200))
                .isInstanceOf(SessionImage.class);
    }

    @Test
    void 생성_유효하지_않은_형식_실패() {
        // expect
        assertThatThrownBy(() -> createSessionImage("test.txt"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_용량초과_실패() {
        // expect
        assertThatThrownBy(() -> createSessionImage(DEFAULT_IMAGE_SIZE + 1, DEFAULT_WIDTH, DEFAULT_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_pixel_실패() {
        // expect
        assertThatThrownBy(() -> createSessionImage(DEFAULT_IMAGE_SIZE, DEFAULT_WIDTH - 1, DEFAULT_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class);
        // expect
        assertThatThrownBy(() -> createSessionImage(DEFAULT_IMAGE_SIZE, DEFAULT_WIDTH, DEFAULT_HEIGHT - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_비율_실패() {
        // expect
        assertThatThrownBy(() -> createSessionImage(DEFAULT_IMAGE_SIZE, DEFAULT_WIDTH + 100, DEFAULT_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class);
    }
}