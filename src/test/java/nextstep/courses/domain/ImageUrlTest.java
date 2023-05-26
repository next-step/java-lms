package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ImageUrlTest {

    @Test
    void testValidateUrl() {
        String imageUrl = "avatars.githubusercontent.com/u/102819554?s=48&v=4";
        assertThatThrownBy(() -> {new ImageUrl(imageUrl);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 url 형식 입니다.");
    }
}