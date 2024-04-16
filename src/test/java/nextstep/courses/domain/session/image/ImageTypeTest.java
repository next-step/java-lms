package nextstep.courses.domain.session.image;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTypeTest {

    @Test
    @DisplayName("종류가 gif, jpg, jpeg, png, svg 이외인 경우 예외를 던진다.")
    void NotExistedImageType_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ImageType.from("notExisted"));
    }
}
