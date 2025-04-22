package nextstep.courses.domain;

import java.util.Collections;
import java.util.List;

public class Images {
    private List<Image> images;

    public Images(List<Image> images) {
        this.images = List.copyOf(images);
    }

    public List<Image> getImages() {
        return Collections.unmodifiableList(images);
    }
}
