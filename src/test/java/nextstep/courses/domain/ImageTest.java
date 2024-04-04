package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ImageTest {


    @DisplayName("생성 테스트")
    @Test
    void create() {
        LocalDate startDate = LocalDate.of(2024, 1, 20);
        LocalDate endDate = LocalDate.of(2024, 3, 20);
        Session session = new Session(startDate, endDate);
        Assertions.assertThat(session.startDate()).isEqualTo(startDate);
        Assertions.assertThat(session.endDate()).isEqualTo(endDate);
    }

    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    public void imageMaxSize1Mb() {
        assertThatThrownBy(() -> new Image(1000001, 0, 0, "test.jpg"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지 타입은 gif,jpg,jpeg,png,svg만 허용한다.")
    @ParameterizedTest
    @CsvSource(value = {"test.jpg", "test.jpeg", "test.gif", "test.png", "test.svg"})
    void matchImages(String fileName) {
        String[] split = fileName.split("\\.");
        System.out.println("s");
        assertThat(Image.Type.from(fileName)).isNotEqualTo(Image.Type.NONE);
    }

    @DisplayName("width 와 height는 각각 300, 200 픽셀 이상이여야 한다.")
    @Test
    void minimumHeightAndWidth() {
        assertThatCode(() -> new Image(1000, 200, 300, ""))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> new Image(1000, 199, 300, ""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Image(1000, 200, 299, ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지 비율은 3:2 이여야 한다.")
    @Test
    void checkRatioThreeToTwo() {
        assertThatCode(() -> new Image(1000, 200, 300, ""))
                .doesNotThrowAnyException();
    }

}