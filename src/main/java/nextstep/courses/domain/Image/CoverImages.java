package nextstep.courses.domain.Image;

import nextstep.courses.exception.CoverImagesEmptyException;

import java.util.Collections;
import java.util.List;

public class CoverImages {

    private final List<CoverImage> values;

    public CoverImages(List<CoverImage> coverImageList) {
        validate(coverImageList);
        this.values = coverImageList;
    }

    private void validate(List<CoverImage> coverImageList) {
        if (coverImageList.isEmpty()) {
            throw new CoverImagesEmptyException("강의는 하나 이상의 커버 이미지를 가져야 합니다");
        }
    }

    public List<CoverImage> find() {
        return Collections.unmodifiableList(values);
    }

}
