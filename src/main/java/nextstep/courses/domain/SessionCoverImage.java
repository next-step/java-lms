package nextstep.courses.domain;

import java.util.Objects;

public class SessionCoverImage {
    private final String url;

    public SessionCoverImage(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionCoverImage that = (SessionCoverImage) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
