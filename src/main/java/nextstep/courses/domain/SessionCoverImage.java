package nextstep.courses.domain;

import java.util.Objects;

public class SessionCoverImage {
    private String path;

    public SessionCoverImage(String path) {
        this.path = path;
    }

    public SessionCoverImage changeImage(String newPath) {
        this.path = newPath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCoverImage that = (SessionCoverImage) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
