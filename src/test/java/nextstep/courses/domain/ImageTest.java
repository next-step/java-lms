package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ImageTest {
    @Test
    void success() {
        Image image = new Image(
                1000L,
                1000,
                Image.Type.GIF,
                300,
                200,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
