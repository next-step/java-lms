package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoverImageTest {

    public static final String INVALID_IMAGE_TYPE = "유효하지 않은 이미지 타입 입니다.";
    private static final String INVALID_IMAGE_FILE_SIZE = "이미지 사이즈는 1MB 이하만 가능합니다.";
    private final static String INVALID_RATIO = "이미지의 가로, 세로 비율은 3:2가 되어야 합니다.";
    private final static String INVALID_HEIGHT = "이미지의 세로 길이는 200px 이상부터 가능합니다.";


    @Test
    @DisplayName("이미지 타입 ImageType(Enum)에 없으면 IllegalStateException")
    void imageTypeTest() {

        String imageType = "EXE";
        int imageWidth = 300;
        int imageHeight = 200;
        int imageFileSize = 1024;

        assertThatThrownBy(() -> {
            CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage(INVALID_IMAGE_TYPE);
    }

    @Test
    @DisplayName("이미지 크기 1MB 초과하면 IllegalStateException")
    void imageFileSizeTest() {
        String imageType = "JPG";
        int imageWidth = 300;
        int imageHeight = 200;
        int imageFileSize = 2048;

        assertThatThrownBy(() -> {
            CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage(INVALID_IMAGE_FILE_SIZE);
    }

    @Test
    @DisplayName("width >= 300 && height >= 200 && width / heigth == 3/2가 아닌 경우 IlleaglArgumentException")
    void imageSizeTest() {
        String imageType = "JPG";
        int imageWidth = 350;
        int imageHeight = 200;
        int imageFileSize = 1024;

        assertThatThrownBy(() -> {
            CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_RATIO);
    }


    @Test
    @DisplayName("커버 이미지 생성 ")
    void coverImageOfTest() {
        String imageType = "JPG";
        int imageWidth = 300;
        int imageHeight = 200;
        int imageFileSize = 1024;

        CoverImage coverImage = CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);

        assertAll(
                () -> assertEquals(coverImage.imageFileSize(), imageFileSize),
                () -> assertEquals(coverImage.imageWidth(), imageWidth),
                () -> assertEquals(coverImage.imageHeight(), imageHeight),
                () -> assertEquals(coverImage.imageType(), imageType)
        );


    }
}