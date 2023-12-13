package nextstep.courses.domain.Image;

import nextstep.courses.exception.CoverImagesEmptyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CoverImagesTest {

    @Test
    @DisplayName("강의 커버 이미지가 한장도 없는 경우 에러를 던진다")
    void 이미지_갯수_에러() {
        List<CoverImage> coverImageList = new ArrayList<>();

        Assertions.assertThatThrownBy(() -> new CoverImages(coverImageList)).isInstanceOf(CoverImagesEmptyException.class);
    }
}
