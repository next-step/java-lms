package nextstep.courses.domain.Image;

import nextstep.courses.exception.FileVolumeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VolumeTest {

    @Test
    @DisplayName("크기는 1MB 이하여야 한다.")
    void 생성_크기_에러() {
        assertThatThrownBy(() -> new Volume(1000_001))
                .isInstanceOf(FileVolumeException.class);
    }
}
