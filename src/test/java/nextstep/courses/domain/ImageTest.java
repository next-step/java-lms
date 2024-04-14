package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ImageTest {
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    public void imageSizeLessThan1MB() {
        //given & when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new ImageSize(1000000);
                }).withMessageMatching("이미지 크기는 1메가 바이트를 넘을 수 없습니다.");
    }
}
