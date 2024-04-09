package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ImageTypeTest {
    @ParameterizedTest
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    void 생성(String type) {
        assertThat(new ImageType(type)).isEqualTo(new ImageType(type));
    }

    @Test
    void 허용하지_않는_이미지_타입() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new ImageType("tiff"))
                .withMessageMatching("지원하지 않는 이미지 파일입니다.");
    }
}