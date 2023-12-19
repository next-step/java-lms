package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionImages {

    private List<SessionImage> images = new ArrayList<>();

    public SessionImages(List<SessionImage> images) {
        if (images.size() < 1) {
            throw new IllegalArgumentException("커버 이미지는 하나 이상이여야 합니다.");
        }
        this.images = images;
    }

    public List<SessionImage> getImages() {
        return Collections.unmodifiableList(images);
    }

    public int size() {
        return this.images.size();
    }

    public void addImage(SessionImage image) {
        images.add(image);
    }
}
