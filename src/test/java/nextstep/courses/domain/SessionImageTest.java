package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class SessionImageTest {

    private Path workingDir;
    @BeforeEach
    void setUp() {
        this.workingDir = Path.of("", "src/test/resources");
    }

    private URI getUri(String fileName) {
        return workingDir.resolve(fileName).toUri();
    }

    @Test
    void 이미지는_1MB이하여야한다() {
        File file = new File(getUri("test_2mb.jpg"));
        assertThatThrownBy(() -> {
            new SessionImage(file);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지는_이미지형식외의_파일은_불가능하다() {
        File file = new File(getUri("test.txt"));
        assertThatThrownBy(() -> {
            new SessionImage(file);
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