package nextstep.courses.domain;

import nextstep.courses.exception.InvalidURLException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class CoverImage {
    private final URL url;
    private final Long id;

    public CoverImage(String url) {
        this(0L, url);
    }

    public CoverImage(Long id, String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new InvalidURLException(e);
        }
        this.id = id;
    }

    public String getUrl() {
        return url.toString();
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
