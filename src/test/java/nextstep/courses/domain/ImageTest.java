package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("이미지 객체 테스트")
class ImageTest {

    @DisplayName("이미지 객체 생성시 유효하지 않은 이미지 url 은 예외가 발생한다")
    @Test
    void invalidImageUrl() {
        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        new Image("test.jpeg", "http://www.../hello^")
                )
                .withMessage("invalid image uri");
    }

    @DisplayName("이미지 객체 생성시 유효하지 않은 이미지 파일명 확장자는 예외가 발생한다")
    @Test
    void invalidFileName() {
        assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        new Image("test.txt", "http://www.hello.com")
                )
                .withMessage("not support image extension");
    }

    @DisplayName("유효한 이미지 파일명과 url을 전달하면 이미지 객체 생성에 성공한다 ")
    @Test
    void validImage() {
        Image image = new Image("test.jpeg", "http://www.nextstep.com");
        assertThat(image.fetchId()).isEqualTo(0L);
        assertThat(image.fetchFileName()).isEqualTo("test.jpeg");
        assertThat(image.fetchCoverImageUrl()).isEqualTo("http://www.nextstep.com");
    }
}
