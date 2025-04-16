package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoverImageTest {

    @Test
    @DisplayName("정상적인 이미지 정보로 생성에 성공한다")
    void createCoverImage_success() {
        CoverImage image = new CoverImage.Builder()
                .fileName("cover.png")
                .imageFormat("png")
                .fileSize(100_000L)
                .imageSize(300, 200)
                .build();

        assertDoesNotThrow(() -> new CoverImage("cover.png", "png", 100_000L, 300, 200));
    }

    @Test
    @DisplayName("파일 크기가 0이면 예외가 발생한다")
    void fileSizeIsZero_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CoverImage("zero.png", "png", 0L, 300, 200);
        });
    }

    @Test
    @DisplayName("파일 크기가 1MB를 초과하면 예외가 발생한다")
    void fileSizeExceedsLimit_throwsException() {
        long fileSize2MB = 2 * 1024 * 1024;
        assertThrows(IllegalArgumentException.class, () ->
                new CoverImage("large.png", "png", fileSize2MB, 300, 200)
        );
    }

    @Test
    @DisplayName("지원되지 않는 포맷일 경우 예외가 발생한다")
    void unsupportedFormat_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new CoverImage("doc.pdf", "pdf", 100_000L, 300, 200)
        );
    }

    @Test
    @DisplayName("이미지 포맷은 대소문자를 구분하지 않는다")
    void formatCaseInsensitive() {
        assertDoesNotThrow(() -> new CoverImage("image.JPG", "Jpg", 100_000L, 300, 200));
    }

    @Test
    @DisplayName("너비 또는 높이가 기준보다 작으면 예외가 발생한다")
    void widthOrHeightTooSmall_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new CoverImage("small.png", "png", 100_000L, 299, 200)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new CoverImage("small.png", "png", 100_000L, 300, 199)
        );
    }

    @Test
    @DisplayName("비율이 3:2가 아니면 예외가 발생한다")
    void invalidAspectRatio_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new CoverImage("bad-ratio.png", "png", 100_000L, 500, 400) // 5:4
        );
    }

    @Test
    @DisplayName("정확한 비율이 3:2일 때는 생성이 가능하다")
    void exactRatio3to2_success() {
        assertDoesNotThrow(() ->
                new CoverImage("cover.png", "svg", 100_000L, 450, 300) // 3:2
        );
    }
}
