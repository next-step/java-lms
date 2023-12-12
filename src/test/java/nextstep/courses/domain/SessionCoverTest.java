package nextstep.courses.domain;

import nextstep.courses.utils.RasterProcessor;
import nextstep.courses.utils.SessionCoverLoader;
import nextstep.courses.utils.VectorProcessor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionCoverTest {

    @Test
    void 커버_생성() throws IOException {
        File file = new File("src/main/resources/cover/pass.svg");
        SessionCover cover = SessionCoverLoader.loadImage("src/main/resources/cover/pass.svg", new VectorProcessor());
        assertThat(cover.image().length).isEqualTo(file.length());
    }

    @Test
    void null_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> SessionCoverLoader.loadImage(null, new RasterProcessor()))
                .withMessage("커버이미지 파일 경로를 입력해주세요.");
    }

    @Test
    void 크기exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> SessionCoverLoader.loadImage("src/main/resources/cover/size.jpeg", new RasterProcessor()))
                .withMessage("이미지 크기는 1MB 이하여야합니다.");
    }

    @Test
    void 타입exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> SessionCoverLoader.loadImage("src/main/resources/cover/type.heic", new RasterProcessor()))
                .withMessage("지원하지 않는 이미지 타입입니다.");
    }

    @Test
    void 비율exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> SessionCoverLoader.loadImage("src/main/resources/cover/proportion.png", new RasterProcessor()))
                .withMessage("가로 세로 비율을 3:2로 맞춰주세요.");
    }

    @Test
    void 픽셀exception() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> SessionCoverLoader.loadImage("src/main/resources/cover/pixel.svg", new VectorProcessor()))
                .withMessage("가로 300픽셀 세로 200픽셀 이상이어야합니다.");
    }
}