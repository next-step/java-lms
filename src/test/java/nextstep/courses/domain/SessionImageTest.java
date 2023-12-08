package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class SessionImageTest {

    @Test
    void 이미지는_1MB이하여야한다() {
        assertThatThrownBy(() -> {
            new SessionImage(1024*1024*2, "jpg", new ImageSize(200, 200));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지는_이미지형식외의_파일은_불가능하다() {
        assertThatThrownBy(() -> {
            new SessionImage(1024, "text", new ImageSize(200, 200));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지는_가로가_300_px보다_작으면_안된다(){
        assertThatThrownBy(() -> {
            new SessionImage(1024, "jpg", new ImageSize(200, 200));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지는_세로가_200_px보다_작으면_안된다(){
        assertThatThrownBy(() -> {
            new SessionImage(1024, "jpg", new ImageSize(200, 100));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지는_비율이_3대2이다(){
        assertThatThrownBy(() -> {
            new SessionImage(1024, "jpg", new ImageSize(1920, 1080));
        }).isInstanceOf(IllegalArgumentException.class);
    }


}