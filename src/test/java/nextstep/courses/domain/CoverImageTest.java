package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CoverImageTest {
    @Test
    @DisplayName("CoverImage 생성")
    void create() {
        assertThat(new CoverImage("pobi.jpeg", 500L, 300D, 200D))
                .isInstanceOf(CoverImage.class);
    }

    @Test
    @DisplayName("CoverImage 생성시, 너비, 높이 비율이 3:2가 아니면 예외 던짐")
    void create_ratio_exception() {
        assertThatThrownBy(()->new CoverImage("pobi.jpeg", 500L, 300D, 300D))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("CoverImage 생성시, 너비나 높이의 최소기준에 부합하면 에외던짐")
    @CsvSource({"299D,200D","300D,199D"})
    void create_width_height_exception(Double width, Double height) {
        assertThatThrownBy(()->new CoverImage("pobi.jpeg", 500L, width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
