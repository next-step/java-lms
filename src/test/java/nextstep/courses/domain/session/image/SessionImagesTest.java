package nextstep.courses.domain.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static nextstep.courses.domain.session.image.SessionImageType.JPEG;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionImagesTest {

    @DisplayName("SessionImages 인스턴스 생성")
    @Test
    public void testConstructor() throws IOException {
        int width = 300;
        int height = 200;
        long byteSize = 1024L * 866L;

        SessionImage image = new SessionImage("https://test", JPEG) {
            @Override
            public int height() {
                return height;
            }

            @Override
            public int width() {
                return width;
            }

            @Override
            public long byteSize() {
                return byteSize;
            }
        };
        assertDoesNotThrow(() -> new SessionImages(List.of(image)));
    }
}
