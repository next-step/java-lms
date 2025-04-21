package nextstep.courses.domain.session.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void delete() {
        value.forEach(SessionImage::delete);
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
