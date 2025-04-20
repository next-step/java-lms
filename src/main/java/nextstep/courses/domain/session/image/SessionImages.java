package nextstep.courses.domain.session.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SessionImages {
    private final List<SessionImage> value;

    public SessionImages() {
        this(new ArrayList<>());
    }

    public SessionImages(List<SessionImage> sessionImages) {
        this.value = sessionImages;
    }

    public void add(SessionImage sessionImage) {
        value.add(sessionImage);
    }

    public int size() {
        return value.size();
    }

    public List<String> urls() {
        return value.stream()
            .map(SessionImage::url)
            .collect(Collectors.toList());
    }

    public List<String> types() {
        return value.stream()
            .map(SessionImage::type)
            .collect(Collectors.toList());
    }

    public List<BufferedImage> images() throws IOException {
        List<BufferedImage> images = new ArrayList<>();
        for (SessionImage sessionImage : value) {
            images.add(sessionImage.image());
        }
        return images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionImages that = (SessionImages) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
