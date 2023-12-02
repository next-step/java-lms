package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.exception.FileException.FileExtensionException;
import org.junit.jupiter.api.Test;

public class ThumbnailTest {

    @Test
    public void 파일확장자_다른_것_사용_시_에러_테스트() {
        assertThatThrownBy(() -> new Thumbnail(1, "thumbnail.xlsx",
                new FileSize(1024L * 1024L), new FileDimensions(300, 200)))
                .isInstanceOf(FileExtensionException.class);
    }
}
