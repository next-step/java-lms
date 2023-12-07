package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.ImageFileInfoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ExtensionTest {

    @DisplayName("파일의 확장자(gif, jpg, jpeg, png, svg)가 존재하면 해당하는 상수를 반환한다.")
    @Test
    void findExtension() throws ImageFileInfoException {
        assertThat(Extension.extension("jpg")).isEqualTo(Extension.JPG);
    }

    @DisplayName("파일의 확장자가 존재하지 않으면 예외를 던진다.")
    @Test
    void extension() {
        assertThatThrownBy(() -> Extension.extension("mp4")).isInstanceOf(ImageFileInfoException.class)
            .hasMessage("해당 확장자는 사용할 수 없는 이미지 파일입니다. 현재 확장자 :: mp4");
    }
}
