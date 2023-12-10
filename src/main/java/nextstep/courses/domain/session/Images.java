package nextstep.courses.domain.session;

import java.util.Collections;
import java.util.List;

public class Images {
    private final List<Image> images;

    public Images(List<Image> images) {
        this.images = images;
    }

    public static Images of(Image... image) {
        return of(List.of(image));
    }

    public static Images of(List<Image> images) {
        return new Images(images);
    }

    public void add(Image image) {
        images.add(image);
    }

    public List<Image> values() {
        return Collections.unmodifiableList(images);
    }

    public int size() {
        return images.size();
    }
}
