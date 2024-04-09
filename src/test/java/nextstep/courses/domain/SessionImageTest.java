package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ImageTest {
    @Test
    void width_300픽셀보다_짧음() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(299, 200, 1_000_000, "jpg"))
                .withMessageMatching("이미지 가로 길이가 300보다 짧습니다.");
    }

    @Test
    void height_200픽셀보다_짧음() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(300, 199, 1_000_000, "jpg"))
                .withMessageMatching("이미지 세로 길이가 200보다 짧습니다.");
    }

    @Test
    void width_height_비율_오류() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(400, 200, 1_000_000, "jpg"))
                .withMessageMatching("이미지 가로, 세로 비율이 맞지 않습니다.");
    }
}
