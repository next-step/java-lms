package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageFileInfoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class HeightTest {

    @DisplayName("이미지의 높이가 200px 미만이면 예외를 발생시킨다.")
    @Test
    void validateWidth() {
        assertThatThrownBy(() -> new Height(199)).isInstanceOf(ImageFileInfoException.class)
            .hasMessage("이미지의 높이는 200이상 이어야 합니다. 현재 높이 :: 199px");
    }
}
