package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;

import java.util.List;

public class Images {

    private List<Image> images;

    public Images(List<Image> images) {
        this.images = images;
    }

    public List<Image> images() {
        return images;
    }

    public int size() {
        return images.size();
    }
}
