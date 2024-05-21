package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.session.InvalidImageConditionsException;
import org.junit.jupiter.api.Test;

class DimensionTest {

    @Test
    public void 이미지는_최소_너비와_높이를_충족해야_한다() {
        assertThatThrownBy(() -> {
            new SessionCoverImage(1L, 100, 180, 10000, "png");
        }).isInstanceOf(InvalidImageConditionsException.class)
            .hasMessageContaining("이미지는 최소 300*200 이상이여야합니다. 입력된 사이즈 100*180");
    }

    @Test
    public void 이미지는_너비와_높이_비율이_존재한다() {
        assertThatThrownBy(() -> {
            new SessionCoverImage(1L, 350, 300, 10000, "png");
        }).isInstanceOf(InvalidImageConditionsException.class)
            .hasMessageContaining("너비와 높이가 3:2 비율이여야 합니다.");
    }
}
