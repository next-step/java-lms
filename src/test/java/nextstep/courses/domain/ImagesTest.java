package nextstep.courses.domain;

import nextstep.courses.exception.ImageNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImagesTest {

    @Test
    @DisplayName("이미지가 없을 경우 에러 발생한다")
    public void images() {
        assertThatExceptionOfType(ImageNotExistException.class)
            .isThrownBy(() -> new Images(Collections.emptyList()))
            .withMessageMatching("이미지 최소 개수를 만족하지 않습니다.");
    }

}
