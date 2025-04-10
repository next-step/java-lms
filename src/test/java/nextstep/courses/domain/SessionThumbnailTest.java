package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionThumbnailTest {

    @Test
    @DisplayName("정상적인 썸네일을 생성한다")
    void create() {
        // given
        String fullFileName = "test.jpg";
        long fileSize = 500 * 1024; // 500KB
        int width = 300;
        int height = 200;

        // when
        SessionThumbnail thumbnail = new SessionThumbnail(fullFileName, fileSize, width, height);

        // then
        assertThat(thumbnail).isNotNull();
    }

    @Test
    @DisplayName("파일 크기가 1MB를 초과하면 예외가 발생한다")
    void validateFileSize() {
        // given
        String fullFileName = "test.jpg";
        long fileSize = 1024 * 1024 + 1; // 1MB + 1byte
        int width = 300;
        int height = 200;

        // when & then
        assertThatThrownBy(() -> new SessionThumbnail(fullFileName, fileSize, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일 크기는 1MB를 초과할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test.txt", "test.doc", "test.exe"})
    @DisplayName("허용되지 않는 확장자로 썸네일을 생성하면 예외가 발생한다")
    void validateExtension(String fullFileName) {
        // given
        long fileSize = 500 * 1024;
        int width = 300;
        int height = 200;

        // when & then
        assertThatThrownBy(() -> new SessionThumbnail(fullFileName, fileSize, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 파일 확장자입니다.");
    }

    @Test
    @DisplayName("이미지 크기가 최소 크기보다 작으면 예외가 발생한다")
    void validateDimensions() {
        // given
        String fullFileName = "test.jpg";
        long fileSize = 500 * 1024;
        int width = 299;
        int height = 199;

        // when & then
        assertThatThrownBy(() -> new SessionThumbnail(fullFileName, fileSize, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지는 최소 300x200 픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이미지 비율이 3:2가 아니면 예외가 발생한다")
    void validateAspectRatio() {
        // given
        String fullFileName = "test.jpg";
        long fileSize = 500 * 1024;
        int width = 300;
        int height = 201; // 300:201는 3:2 비율이 아님

        // when & then
        assertThatThrownBy(() -> new SessionThumbnail(fullFileName, fileSize, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지는 3:2 비율이어야 합니다.");
    }

    @Test
    @DisplayName("3:2 비율의 이미지를 생성할 수 있다")
    void createWithValidAspectRatio() {
        // given
        String fullFileName = "test.jpg";
        long fileSize = 500 * 1024;
        int width = 600;
        int height = 400; // 600:400은 3:2 비율

        // when
        SessionThumbnail thumbnail = new SessionThumbnail(fullFileName, fileSize, width, height);

        // then
        assertThat(thumbnail).isNotNull();
    }

    @Test
    @DisplayName("올바르지 않은 파일명 형식이면 예외가 발생한다")
    void validateFileNameFormat() {
        // given
        String fullFileName = "test"; // 확장자 없음
        long fileSize = 500 * 1024;
        int width = 300;
        int height = 200;

        // when & then
        assertThatThrownBy(() -> new SessionThumbnail(fullFileName, fileSize, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 파일명 형식이 아닙니다.");
    }
} 