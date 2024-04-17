package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoverImageTest {
    @Test
    void 생성자_예외_파일사이즈() {
        Long fileSize = 1024L * 2;
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new CoverImage(fileSize, ImageExtension.GIF.name(), 300L, 200L);
        });
    }

    @Test
    void 생성자_예외_그림사이즈() {
        //width 300미만
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new CoverImage(1024L, ImageExtension.GIF.name(), 299L, 200L);
        });

        //width 200미만
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new CoverImage(1024L, ImageExtension.GIF.name(), 300L, 199L);
        });

        //width와 height의 비율은 3:2이 아닌 경우
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new CoverImage(1024L, ImageExtension.GIF.name(), 600L, 200L);
        });
    }

    @Test
    void 생성자_예외_그림사이즈_파일확장자() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new CoverImage(1024L, "java", 300L, 200L);
        });
    }
}
