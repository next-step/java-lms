package nextstep.courses.domain.session.image;

import nextstep.stub.TestImageHandler;
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
        SessionImage image = new SessionImage("https://test", new TestImageHandler(), JPEG);
        assertDoesNotThrow(() -> new SessionImages(List.of(image)));
    }
}
