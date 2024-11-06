package nextstep.courses.domain;

import nextstep.courses.InvalidCoverImageException;
import nextstep.courses.domain.CoverImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {
    private static final String IMAGE_PATH = System.getProperty("user.dir") + "/src/test/java/nextstep/courses/resources/";

    @Test
    void new_커버_이미지를_생성하면_커버_이미지가_생성된다() throws IOException {
        Assertions.assertThat(new CoverImage(IMAGE_PATH + "validCoverImage.png")).isInstanceOf(CoverImage.class);
    }

    @Test
    void new_1MB를_초과하는_커버_이미지는_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(IMAGE_PATH + "exceededCoverImage.png")).isInstanceOf(
                        InvalidCoverImageException.class)
                .hasMessage("유효하지 않은 용량의 커버 이미지입니다.");
    }

    @Test
    void new_허용되지_않은_확장자의_커버_이미지는_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(IMAGE_PATH + "exceededCoverImage.xlsx")).isInstanceOf(
                        InvalidCoverImageException.class)
                .hasMessage("유효하지 않은 확장자의 커버 이미지입니다.");
    }

    @Test
    void new_허용되지_않은_크기의_커버_이미지는_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(IMAGE_PATH + "smallCoverImage.png")).isInstanceOf(
                        InvalidCoverImageException.class)
                .hasMessage("유효하지 않은 크기의 커버 이미지입니다.");
    }

    @Test
    void new_허용되지_않은_비율의_커버_이미지는_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(IMAGE_PATH + "unbalancedCoverImage.png")).isInstanceOf(
                        InvalidCoverImageException.class)
                .hasMessage("유효하지 않은 비율의 커버 이미지입니다.");
    }
}
