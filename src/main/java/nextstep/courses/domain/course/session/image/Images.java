package nextstep.courses.domain.course.session.image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Images implements Iterable<Image> {
    private final List<Image> images;

    public Images() {
        this(new ArrayList<>());
    }

    public Images(List<Image> images) {
        validate(images);

        this.images = images;
    }

    private void validate(List<Image> images) {
        checkImagesIsNull(images);
    }

    private void checkImagesIsNull(List<Image> images) {
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("이미지는 최소 1개 이상 존재해야 합니다.");
        }
    }

    @Override
    public Iterator<Image> iterator() {
        return this.images.iterator();
    }
}
