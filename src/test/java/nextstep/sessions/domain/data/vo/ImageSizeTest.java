package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.coverimage.ImageSize;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSizeTest {

    @Test
    void 이미지_파일_크기_제한() {
        assertThatThrownBy(() -> new ImageSize(1048577, 300, 200))
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미지 파일 크기가 초과했습니다.");
    }

}
