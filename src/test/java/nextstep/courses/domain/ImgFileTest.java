package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImgFileTest {


    @Test
    void 이미지_파일형식_테스트() {
        assertThatThrownBy(() ->new ImgFile("test"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 이미지 파일 형식이 아닙니다.");
    }

    @Test
    void 이미지_파일_널체크() {
        assertThatThrownBy(() ->new ImgFile(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지는 필수 값입니다. 입력을 확인해 주세요.");
    }
}