package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CourseFreeTest {

    private static final String TEST_TITLE = "자바 기초 강의";
    private static final Long TEST_CREATOR_ID = 1L;
    private static final String TEST_RESOURCES_PATH = "src/test/resources/images";
    private static final String TEST_COVER_IMAGE_PATH = TEST_RESOURCES_PATH + "/valid.jpg";
    
    private CourseFree courseFree;

    @BeforeEach
    void setUp() throws IOException {
        // Create test resources directory if it doesn't exist
        File testDir = new File(TEST_RESOURCES_PATH);
        if (!testDir.exists()) {
            testDir.mkdirs();
        }

        // Create valid image (400x300)
        createTestImage(400, 300, "valid.jpg");

        courseFree = new CourseFree(TEST_TITLE, TEST_CREATOR_ID, TEST_COVER_IMAGE_PATH);
    }

    @Test
    @DisplayName("무료 강의를 생성 테스트")
    void createCourseFree() {
        // then
        assertThat(courseFree).isNotNull();
        assertThat(courseFree.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(courseFree.getCreatorId()).isEqualTo(TEST_CREATOR_ID);
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없음")
    void courseFreeHasNoMaxAttendeesLimit() {
        // then
        assertThat(courseFree.getMaxAttendees()).isEqualTo(Long.MAX_VALUE);
    }

    private File createTestImage(int width, int height, String fileName) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        File imageFile = new File(TEST_RESOURCES_PATH, fileName);
        ImageIO.write(image, "jpg", imageFile);
        return imageFile;
    }
} 