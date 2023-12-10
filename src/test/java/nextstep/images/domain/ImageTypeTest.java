package nextstep.images.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {
    @Test
    @DisplayName("허용되지 않은 이미지 타입은 만들 수 없다.")
    void test1(){
        String type = "docx";
        assertThatThrownBy(() -> ImageType.of(type))
                .hasMessageContaining("허용되지 않은 이미지 타입입니다.");
    }
}