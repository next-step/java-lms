package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    @Test
    @DisplayName("이미지 크기가 1(MB)이상일 경우 예외를 던진다.")
    void size_test() {
        assertThatThrownBy(() -> new CoverImage(2))
                .isInstanceOf(ImageSizeOverException.class)
                .hasMessageContaining("이미지 크기는 1MB를 초과할 수 없습니다.");
    }
}
