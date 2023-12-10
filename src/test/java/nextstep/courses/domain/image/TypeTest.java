package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.image.Type;
import nextstep.courses.exception.InvalidTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    @DisplayName("이미지 타입은 gif, jpg, jpeg, png, svg만 허용합니다")
    void type() {
        assertThatThrownBy(() -> {
            Type.value("heic");
        }).isInstanceOf(InvalidTypeException.class);
        assertThatThrownBy(() -> {
            Type.value("bmp");
        }).isInstanceOf(InvalidTypeException.class);
    }
}