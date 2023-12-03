package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.exception.FileException.FileSizeException;
import org.junit.jupiter.api.Test;

public class FileSizeTest {

    @Test
    public void 파일_크기_1MB_초과_시_에러_테스트() {
        assertThatThrownBy(() -> new FileSize(1024L * 1024L + 1))
                .isInstanceOf(FileSizeException.class);
    }
}
