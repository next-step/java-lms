package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.exception.FileException.FileDimensionsException;
import org.junit.jupiter.api.Test;

public class ThumbnailDimensionsTest {

    @Test
    public void 파일_너비_300픽셀_미만_시_에러_테스트() {
        assertThatThrownBy(() -> new ThumbnailDimensions(299, 200))
                .isInstanceOf(FileDimensionsException.class);
    }

    @Test
    public void 파일_높이_200픽셀_미만_시_에러_테스트() {
        assertThatThrownBy(() -> new ThumbnailDimensions(300, 199))
                .isInstanceOf(FileDimensionsException.class);
    }

    @Test
    public void 파일_너비_높이_비율_3_대_2_아닐_시_에러_테스트() {
        assertThatThrownBy(() -> new ThumbnailDimensions(300, 300))
                .isInstanceOf(FileDimensionsException.class);
    }
}
