package nextstep.courses.domain.image;

import nextstep.courses.exception.ImagesSizeZeroException;

import java.util.ArrayList;
import java.util.List;

public class Images {

    private final List<Image> values;

    public Images(Image image) {
        this(new ArrayList<>()
             {{ add(image); }}
        );
    }

    public Images(List<Image> values) {
        validateImagesSize(values);
        this.values = values;
    }

    private void validateImagesSize(List<Image> values) {
        if (values.isEmpty()) {
            throw new ImagesSizeZeroException();
        }
    }
}
