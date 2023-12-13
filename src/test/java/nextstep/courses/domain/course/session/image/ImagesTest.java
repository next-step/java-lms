package nextstep.courses.domain.course.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImagesTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Images 은 빈 값이 주어지면 예외를 던진다.")
    void newObject_null_throwsException(List<Image> images) {
        assertThatThrownBy(
                () -> new Images(images)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
