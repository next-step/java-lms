package nextstep.courses.domain.session.image;

import nextstep.stub.domain.TestSessionImage;
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
        SessionImage image = new TestSessionImage("https://test", JPEG, 300, 200, 1024L * 866L);

        assertDoesNotThrow(() -> new SessionImages(List.of(image)));
    }
}
