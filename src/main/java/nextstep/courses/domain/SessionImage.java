package nextstep.courses.domain;

public class SessionImage {
    private final String url;
    private final int width;
    private final int height;

    public SessionImage(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public boolean isValid() {
        return url != null && !url.isBlank() && width > 0 && height > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionImage that = (SessionImage) o;
        return width == that.width && height == that.height && url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(url, width, height);
    }
} 