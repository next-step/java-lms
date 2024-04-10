package nextstep.courses.domain.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.image.ImageFileSize.INVALID_IMAGE_FILE_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImageFileSizeTest {

    @Test
    @DisplayName("이미지 파일 크기 정상 생성")
    void testImageFileSize() {
        // when
        ImageFileSize imageFileSize = ImageFileSize.of(1024);

        // then
        assertThat(imageFileSize.getImageFileSize()).isEqualTo(1024);
    }

    @Test
    @DisplayName("이미지 파일 크기가 1MB를 초과하여 객체 생성 실패")
    void testImageFileSize_over1MB_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ImageFileSize.of(1025);
        }).withMessageContaining(INVALID_IMAGE_FILE_SIZE);
    }

}
