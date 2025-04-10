package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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