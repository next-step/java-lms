package nextstep.course;

import nextstep.session.ImageExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
-  gif, jpg, jpeg, png, svg 확장자가 아니면 예외를 발생시킨다.
*/
public class ImageExtensionTest {

    @DisplayName("gif, jpg, jpeg, png, svg 확장자가 아니면 예외를 발생시킨다.")
    @Test
    void confirmImageExtensionTest() {
        assertThatThrownBy(() -> ImageExtension.confirmImageExtension("test.mp4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 이미지 확장자입니다.");
    }
}
