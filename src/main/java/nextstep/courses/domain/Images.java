package nextstep.courses.domain;

import nextstep.courses.exception.ImageNotExistException;

import java.util.List;
import java.util.Objects;

public class Images {

    private final List<Image> images;

    public Images(List<Image> images) {
        this.images = images;
        validate();
    }

    public void validate() {
        if (this.images.size() < 1) {
            throw new ImageNotExistException("이미지 최소 개수를 만족하지 않습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Images)) return false;
        Images images1 = (Images) o;
        return Objects.equals(images, images1.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(images);
    }

    @Override
    public String toString() {
        return "Images{" +
            "images=" + images +
            '}';
    }
}
