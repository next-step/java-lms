package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageSizeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WidthTest {

    @DisplayName("이미지의 너비가 300px 미만이면 예외를 발생시킨다.")
    @Test
    void validateWidth() {
        Assertions.assertThatThrownBy(() -> new Width(299)).isInstanceOf(ImageSizeException.class)
            .hasMessage("이미지의 너비는 300이상 이어야 합니다. 현재 너비 :: 299px");
    }
}
