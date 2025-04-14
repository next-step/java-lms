package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ImageTest {
    private float fileSize; // bytes
    private String fileType;
    private int width;
    private int height;

    @BeforeEach
    void setUp() {
        fileSize = 1024;
        fileType = "jpg";
        width = 300;
        height = 200;
    }

    @Nested
    class ValidCases {
        @Test
        void shouldAllow_WhenAllConditionsAreValid() {
            assertDoesNotThrow(() -> new Image(fileSize, fileType, width, height));
        }
    }


    @Nested
    class InvalidCases {
        @Test
        void shouldNotAllow_WhenFileSizeIsOver1MB() {
            fileSize = 1025;
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Image(fileSize, fileType, width, height))
                    .withMessage("FileSize Should be under or equal to 1MB");
        }

        @Test
        void shouldNotAllow_WhenGivenInvalidFileType() {
            fileType = "tiff";
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Image(fileSize, fileType, width, height))
                    .withMessage("Allowed file types are only gif, jpg/jpeg,png, svg");
        }

        @Test
        void shouldNotAllow_WhenImageRatioIsNot3to2() {
            width = 2;
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Image(fileSize, fileType, width, height))
                    .withMessage("The ratio of width:height must be 3:2");
        }
    }
}
