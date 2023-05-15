package nextstep.courses.domain;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class SessionCoverImage {
    private final String url;

    public SessionCoverImage(String url) {
        if (!this.isValidURL(url)) {
            throw new IllegalArgumentException("url 형식이 맞지 않습니다.");
        }

        this.url = url;
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
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
