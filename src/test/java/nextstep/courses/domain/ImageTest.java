package nextstep.courses.domain;

import nextstep.sessions.domain.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ImageTest {

    @Test
    @DisplayName("강의 커버 이미지의 크기가 1MB보다 크면 오류가 발생")
    void imageByteTest() {
        assertThatThrownBy(
                () -> new Image(1001, "gif", 300, 200)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 크기는 1MB");
    }

}
