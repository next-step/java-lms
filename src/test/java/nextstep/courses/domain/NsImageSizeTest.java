package nextstep.courses.domain;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NsImageSizeTest {
    @Test
    void 유효한_이미지_사이즈일_경우_객체생성() {
        assertThat(new NsImageSize(600, 400))
                .isInstanceOf(NsImageSize.class);
    }

    @Test
    void 너비가_300px_미만_생성실패() {
        assertThatThrownBy(() -> new NsImageSize(250, 400))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 이미지 사이즈가 아닙니다.");
    }

    @Test
    void 높이가_200px_미만_생성실패() {
        assertThatThrownBy(() -> new NsImageSize(600, 150))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 이미지 사이즈가 아닙니다.");
    }

    @Test
    void 이미지_비율이_3대2가아님_생성실패() {
        assertThatThrownBy(() -> new NsImageSize(600, 500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 width와 height의 비율은 3:2여야 합니다.");
    }}
