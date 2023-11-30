package nextstep.sessions.domain;

import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    @Test
    void 이미지_타입_제한() {
        assertThatThrownBy(() -> new CoverImage("image.heif", 1048576, 300, 200, "/images/"))
            .isInstanceOf(SessionsException.class)
            .hasMessage("지원하지 않는 이미지 타입입니다.");
    }

    @Test
    void 이미지_파일_크기_제한() {
        assertThatThrownBy(() -> new CoverImage("image.png", 1048577, 300, 200, "/images/"))
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미지 파일 크기가 초과했습니다.");
    }


}