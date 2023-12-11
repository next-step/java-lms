package nextstep.courses.domain.coverimage;

import nextstep.courses.MaxImageSizeExceededException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    @Test
    @DisplayName("이미지 크기가 1MB 초과 시 에러 발생")
    public void 이미지_크기_초과() {
        assertThatThrownBy(() -> {
            new CoverImage(1025, "jpg", 300, 200);
        }).isInstanceOf(MaxImageSizeExceededException.class);
    }

    @Test
    public void 이미지_크기_정상() {
        Assertions.assertThatCode(() -> {
            new CoverImage(1023, "jpg", 300, 200);
        }).doesNotThrowAnyException();
    }
}
