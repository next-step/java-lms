package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SessionImageTest {
    @Test
    void testValidImage_확장자예외() {
        Assertions.assertThatThrownBy(() -> new SessionImage(1L, "jpgee", 1024, 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testValidImage_최대사이즈예외() {
        Assertions.assertThatThrownBy(() -> new SessionImage(1L, "jpg", 1572864, 100, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testValidImage_생성확인() {
        SessionImage sessionImage = new SessionImage(1L, "jpg", 1024, 300, 200);
        assertNotNull(sessionImage);
    }

    @Test
    void testValidImage_비율예외() {
        Assertions.assertThatThrownBy(() -> new SessionImage(1L, "jpg", 1024, 300, 800))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testValidImage_최소픽셀예외() {
        Assertions.assertThatThrownBy(() -> new SessionImage(1L, "jpg", 1024, 100, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
