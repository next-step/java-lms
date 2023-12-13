package nextstep.courses.domain.course.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTypeTest {
    @Test
    @DisplayName("find는 존재하지 않는 값을 입력하면 찾을 수 없다는 예외를 던진다.")
    void find_notExistedName_throwsException() {
        assertThatThrownBy(
                () -> ImageType.find("abcd")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
