package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionCoverImage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class SessionCoverImageTest {
    @Test
    void 커버_이미지_확인(){
        SessionCoverImage sessionCoverImage = new SessionCoverImage();
        assertTrue(sessionCoverImage.isValidCoverImage());
    }
}
