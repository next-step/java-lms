package nextstep.image.domain;

import nextstep.image.exception.InvalidImageUrlException;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {
    @DisplayName("이미지는 하나의 이미지링크를 갖는다")
    @Test
    public void imageLink() {
        //given
        Image image = TestFixture.RED_IMAGE;
        //when
        //then
        assertThat(image.getImageUrl())
                .as("이미지 링크가 존재해야 한다")
                .isNotEmpty();
        assertThatThrownBy(() -> {
            Image.of(null);
        }).isInstanceOf(InvalidImageUrlException.class)
                .as("이미지 도메인에서 URL 이 유효하지 않은경우 예외 던져야함을 검증한다")
                .hasMessageContaining("ImageUrl 이 유효하지 않습니다");
    }
}