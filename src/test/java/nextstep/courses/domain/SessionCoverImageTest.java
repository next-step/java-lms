package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionCoverImageTest {

    @DisplayName("이미지 용량이 1MB 보다 크다면 예외가 발생한다.")
    @Test
    void test01() {
        int sizeOfBytes = SessionCoverImage.MAX_SIZE + 1;
        assertThatThrownBy(() -> new SessionCoverImage(sizeOfBytes, ImageType.GIF, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 용량은 1MB 이하여야 합니다.");
    }

    @DisplayName("이미지의 가로 크기가 300px 보다 작거나, 높이가 200px 보다 작으면 예외가 발생한다.")
    @Test
    void test02() {
        assertThatThrownBy(() -> new SessionCoverImage(1000, ImageType.GIF, 90, 60))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로 크기는 300px 이상, 높이는 200px 이상이어야 합니다.");
    }

    @DisplayName("이미지의 가로 크기와 높이의 비율이 3:2 가 아니면 예외가 발생한다.")
    @Test
    void test03() {
        assertThatThrownBy(() -> new SessionCoverImage(1000, ImageType.GIF, 301, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로 크기와 높이의 비율은 3:2 여야 합니다.");
    }
}
