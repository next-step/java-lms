package nextstep.courses.domain;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NsFileTest {
    @Test
    @Order(10)
    void 유효한_이미지파일일_경우_객체생성() {
        assertThat(new NsFile(900_000, "image/jpeg"))
                .isInstanceOf(NsFile.class);
    }

    @Test
    @Order(20)
    void 파일크기가_1MB를_초과_생성실패() {
        assertThatThrownBy(() -> new NsFile(1_000_001, "image/jpeg"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("파일 크기는 1MB를 초과할 수 없습니다.");
    }

    @Test
    @Order(30)
    void 허용되지_않은_파일타입_생성실패() {
        assertThatThrownBy(() -> new NsFile(900_000, "image/webp"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 이미지 파일이 아닙니다.");
    }
}
