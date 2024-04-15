package nextstep.courses.domain.image;

import nextstep.courses.domain.session.Session;

import java.util.List;

public class Images {
    private List<Image> images;

    private Images(List<Image> images) {
        this.images = images;
    }

    public static Images from(List<Image> images) {
        return new Images(images);
    }

    public void addAll(List<Image> images) {
        this.images.addAll(images);
    }
}
