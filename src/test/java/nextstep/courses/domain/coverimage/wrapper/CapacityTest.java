package nextstep.courses.domain.coverimage.wrapper;

import nextstep.courses.exception.MaxUploadSizeExceededException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CapacityTest {

    @DisplayName("파일의 용량이 1MB를 초과하거나 음수면 예외를 발생시킨다.")
    @Test
    void validateCapacity() {
        assertThatThrownBy(() -> new Capacity(1048577)).isInstanceOf(MaxUploadSizeExceededException.class)
            .hasMessage("썸네일 이미지의 용량은 1MB이하 이어야 합니다.");
    }
}
