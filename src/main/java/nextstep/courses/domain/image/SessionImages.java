package nextstep.courses.domain.image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionImages {
    private final List<SessionImage> images;

    public SessionImages(SessionImage image) {
        this(List.of(image));
    }

    public SessionImages(List<SessionImage> images) {
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("커버 이미지는 최소 1개 이상이어야 합니다.");
        }
        this.images = new ArrayList<>(images);
    }

    public int size() {
        return images.size();
    }

    public SessionImage getFirstImage() {
        return images.get(0);
    }

    public List<SessionImage> getImages() {
        return Collections.unmodifiableList(images);
    }
}
