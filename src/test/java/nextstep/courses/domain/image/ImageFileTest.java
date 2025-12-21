package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageFileTest {

    @Test
    void 파일_크기가_기준을_초과한다면_예외가_발생한다() {
        assertThatThrownBy(() -> new ImageFile("파일이름.png", 1024 * 1025))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("이미지 크기는 1MB 이하여야 합니다.");
    }
}
