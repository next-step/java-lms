package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FileSizeTest {
    static FileSize NORMAL_FILE_SIZE = new FileSize(300, 200);

    @DisplayName("파일 너비가 최소 너비보다 작다면 예외발생")
    @Test
    void 너비_확인() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FileSize(250, 200))
                .withMessage("이미지 너비가 너무 작습니다.");
    }

    @DisplayName("파일 높이가 최소 높이보다 작다면 예외발생")
    @Test
    void 높이_확인() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FileSize(300, 150))
                .withMessage("이미지 높이가 너무 작습니다.");
    }

    @DisplayName("파일 비율이 지원하지 않는 비율이라면 예외발생")
    @Test
    void 너비_높이_비율_확인() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FileSize(400, 200))
                .withMessage("이미지의 비율이 맞지 않습니다.");
    }
}