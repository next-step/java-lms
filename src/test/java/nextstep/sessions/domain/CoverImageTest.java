package nextstep.sessions.domain;

import nextstep.utils.Image;
import nextstep.utils.TestImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CoverImageTest {
    @DisplayName("이미지 크기가 1MB를 초과하는 경우 예외 발생")
    @Test
    void testImageSizeExceeds1MB() {
        Image testImage = new TestImage(300, 300, 1024*1024*2, "jpeg");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CoverImage(testImage))
                .withMessage("파일 용량이 1MB를 초과합니다.");
    }

    @DisplayName("허용되지 않는 파일 형식인 경우 예외 발생")
    @Test
    void testInvalidFileExtension() {
        Image testImage = new TestImage(300, 300, 1024*1024, "exe");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CoverImage(testImage))
                .withMessage("허용되지 않는 파일 형식입니다.");
    }

    @DisplayName("너비가 300px 미만인 경우 예외 발생")
    @Test
    void testImageWidthLessThan300px() {
        Image testImage = new TestImage(200, 300, 1024*1024, "jpeg");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CoverImage(testImage))
                .withMessage("이미지 너비는 300px 이상이어야 합니다.");
    }

    @DisplayName("높이가 200px 미만인 경우 예외 발생")
    @Test
    void testImageHeightLessThan200px() {
        Image testImage = new TestImage(300, 100, 1024*1024, "jpeg");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CoverImage(testImage))
                .withMessage("이미지 높이는 200px 이상이어야 합니다.");
    }

    @DisplayName("너비와 높이 비율이 3:2가 아닌 경우 예외 발생")
    @Test
    void testImageAspectRatioNot3to2() {
        Image testImage = new TestImage(300, 500, 1024*1024, "jpeg");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CoverImage(testImage))
                .withMessage("이미지 비율이 3:2가 아닙니다.");
    }

    @DisplayName("커버 이미지 생성 테스트")
    @Test
    void testCreateCoverImage() {
        Image testImage = new TestImage(300, 200, 1024*1024, "jpeg");
        CoverImage coverImage = new CoverImage(testImage);
        assertThat(coverImage.getImage()).isEqualTo(testImage);
    }
}
