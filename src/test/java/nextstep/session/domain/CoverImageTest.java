package nextstep.session.domain;

import nextstep.session.domain.exception.InvalidImageFileSizeException;
import nextstep.session.domain.exception.InvalidImageSizeException;
import nextstep.session.domain.exception.UnsupportedImageFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoverImageTest {

    @Test
    @DisplayName("정상적인 이미지 정보로 생성에 성공한다")
    void createCoverImage_success() {
        assertDoesNotThrow(() ->
                new CoverImage.Builder()
                        .fileName("cover.png")
                        .imageFormat("png")
                        .fileSize(100_000L)
                        .imageSize(300, 200)
                        .build()
        );
    }

    @Test
    @DisplayName("파일 크기가 0이면 예외가 발생한다")
    void fileSizeIsZero_throwsException() {
        assertThrows(InvalidImageFileSizeException.class, () -> {
            new CoverImage.Builder()
                    .fileName("zero.png")
                    .imageFormat("png")
                    .fileSize(0L)
                    .imageSize(300, 200)
                    .build();
        });
    }

    @Test
    @DisplayName("파일 크기가 1MB를 초과하면 예외가 발생한다")
    void fileSizeExceedsLimit_throwsException() {
        long fileSize2MB = 2 * 1024 * 1024;

        assertThrows(InvalidImageFileSizeException.class, () ->
                new CoverImage.Builder()
                        .fileName("large.png")
                        .imageFormat("png")
                        .fileSize(fileSize2MB)
                        .imageSize(300, 200)
                        .build()
        );
    }

    @Test
    @DisplayName("지원되지 않는 포맷일 경우 예외가 발생한다")
    void unsupportedFormat_throwsException() {
        assertThrows(UnsupportedImageFormatException.class, () ->
                new CoverImage.Builder()
                        .fileName("doc.pdf")
                        .imageFormat("pdf")
                        .fileSize(100_000L)
                        .imageSize(300, 200)
                        .build()
        );
    }

    @Test
    @DisplayName("이미지 포맷은 대소문자를 구분하지 않는다")
    void formatCaseInsensitive() {
        assertDoesNotThrow(() ->
                new CoverImage.Builder()
                        .fileName("image.jpg")
                        .imageFormat("JPG")
                        .fileSize(100_000L)
                        .imageSize(300, 200)
                        .build()
        );
    }

    @Test
    @DisplayName("너비 또는 높이가 기준보다 작으면 예외가 발생한다")
    void widthOrHeightTooSmall_throwsException() {
        assertThrows(InvalidImageSizeException.class, () ->
                new CoverImage.Builder()
                        .fileName("small.jpg")
                        .imageFormat("jpg")
                        .fileSize(100_000L)
                        .imageSize(299, 200)
                        .build()
        );
        assertThrows(InvalidImageSizeException.class, () ->
                new CoverImage.Builder()
                        .fileName("small.jpg")
                        .imageFormat("jpg")
                        .fileSize(100_000L)
                        .imageSize(300, 199)
                        .build()
        );
    }

    @Test
    @DisplayName("비율이 3:2가 아니면 예외가 발생한다")
    void invalidAspectRatio_throwsException() {
        assertThrows(InvalidImageSizeException.class, () ->
                new CoverImage.Builder()
                        .fileName("cover.png")
                        .imageFormat("png")
                        .fileSize(100_000L)
                        .imageSize(500, 400) // 5:4
                        .build()
        );
    }

    @Test
    @DisplayName("정확한 비율이 3:2일 때는 생성이 가능하다")
    void exactRatio3to2_success() {
        assertDoesNotThrow(() ->
                new CoverImage.Builder()
                        .fileName("cover.png")
                        .imageFormat("png")
                        .fileSize(100_000L)
                        .imageSize(450, 300) // 3:2
                        .build()
        );
    }
}
