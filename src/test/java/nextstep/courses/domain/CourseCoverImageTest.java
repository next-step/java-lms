package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CourseCoverImageTest {

    private static final String TEST_RESOURCES_PATH = "src/test/resources/images";
    private File validImageFile;
    private File smallImageFile;
    private File invalidImageFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create test resources directory if it doesn't exist
        File testDir = new File(TEST_RESOURCES_PATH);
        if (!testDir.exists()) {
            testDir.mkdirs();
        }

        // Create valid image (400x300)
        validImageFile = createTestImage(400, 300, "valid.jpg");
        
        // Create small image (200x150)
        smallImageFile = createTestImage(200, 150, "small.jpg");
        
        // Create invalid image file (not an image)
        invalidImageFile = new File(TEST_RESOURCES_PATH, "invalid.jpg");
        invalidImageFile.createNewFile();
    }

    @Test
    @DisplayName("유효한 이미지 파일로 CourseCoverImage를 생성할 수 있다")
    void createCourseCoverImageWithValidImage() {
        // when
        CourseCoverImage courseCoverImage = new CourseCoverImage(validImageFile.getAbsolutePath());

        // then
        assertNotNull(courseCoverImage);
    }

    @Test
    @DisplayName("지원하지 않는 이미지 확장자면 예외가 발생한다")
    void throwExceptionWhenImageExtensionIsNotSupported() {
        // when & then
        assertThatThrownBy(() -> {
            new CourseCoverImage("invalid.txt");
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("지원하지 않는 이미지 형식입니다. (지원 형식: gif, jpg, jpeg, png, svg)");
    }

    @Test
    @DisplayName("이미지 파일이 존재하지 않으면 예외가 발생한다")
    void throwExceptionWhenImageFileDoesNotExist() {
        // when & then
        assertThatThrownBy(() -> {
            new CourseCoverImage("nonexistent.jpg");
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("강의 커버 이미지를 읽을 수 없습니다.");
    }

    @Test
    @DisplayName("이미지 크기가 최소 크기보다 작으면 예외가 발생한다")
    void throwExceptionWhenImageSizeIsTooSmall() {
        // when & then
        assertThatThrownBy(() -> {
            new CourseCoverImage(smallImageFile.getAbsolutePath());
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("강의 커버 이미지는 최소 300x200 픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("유효하지 않은 이미지 파일이면 예외가 발생한다")
    void throwExceptionWhenImageFileIsInvalid() {
        // when & then
        assertThatThrownBy(() -> {
            new CourseCoverImage(invalidImageFile.getAbsolutePath());
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("강의 커버 이미지를 읽을 수 없습니다.");
    }

    private File createTestImage(int width, int height, String fileName) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        File imageFile = new File(TEST_RESOURCES_PATH, fileName);
        ImageIO.write(image, "jpg", imageFile);
        return imageFile;
    }
} 