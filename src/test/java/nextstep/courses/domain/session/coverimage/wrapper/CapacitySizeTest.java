package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageCapacitySizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CapacitySizeTest {

    @DisplayName("파일의 용량이 1MB를 초과하면 예외를 발생시킨다.")
    @Test
    void validateCapacityMax() {
        assertThatThrownBy(() -> new CapacitySize(1048577)).isInstanceOf(ImageCapacitySizeException.class)
            .hasMessage("썸네일 이미지의 용량 크기는 1MB 이하 이어야 합니다. 현재 크기 :: 1048577bytes");
    }

    @DisplayName("파일의 용량이 1byte 미만이면 예외를 발생시킨다.")
    @Test
    void validateCapacityMin() {
        assertThatThrownBy(() -> new CapacitySize(0)).isInstanceOf(ImageCapacitySizeException.class)
            .hasMessage("썸네일 이미지의 용량 크기는 1byte 이상 이어야 합니다. 현재 크기 :: 0bytes");
    }
}
