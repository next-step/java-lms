package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.coverImage.ImageExtension;
import nextstep.courses.domain.session.coverImage.SessionCoverImage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionCoverImageTest {
    @Test
    void 커버_이미지_확인__유효한_이미지() {
        SessionCoverImage image1 = new SessionCoverImage(1, ImageExtension.GIF, 300, 200);
        assertTrue(image1.isValidCoverImage());
    }

    @Test
    void 커버_이미지_확인__유효하지_않은_확장자() {
        SessionCoverImage image2 = new SessionCoverImage(1, ImageExtension.BMP, 300, 200);
        assertFalse(image2.isValidCoverImage());
    }

    @Test
    void 커버_이미지_확인__유효하지_않은_이미지_사이즈() {
        SessionCoverImage image1 = new SessionCoverImage(2, ImageExtension.GIF, 200, 200);
        assertFalse(image1.isValidCoverImage());
    }

    @Test
    void 커버_이미지_확인__유효하지_않은_이미지_너비_높이() {
        SessionCoverImage image2 = new SessionCoverImage(1, ImageExtension.GIF, 200, 200);
        assertFalse(image2.isValidCoverImage());
        SessionCoverImage image3 = new SessionCoverImage(1, ImageExtension.GIF, 300, 300);
        assertFalse(image3.isValidCoverImage());
        SessionCoverImage image4 = new SessionCoverImage(1, ImageExtension.GIF, 300, 400);
        assertFalse(image4.isValidCoverImage());
    }
}
