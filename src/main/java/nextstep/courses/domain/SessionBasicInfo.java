package nextstep.courses.domain;

public class SessionBasicInfo {
    private final String title;
    private final SessionImage image;

    public SessionBasicInfo(String title, SessionImage image) {
        this.title = title;
        this.image = image;
    }

    public boolean isImageValid() {
        return image != null && image.isValid();
    }

    public boolean hasTitle(String title) {
        return this.title.equals(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionBasicInfo that = (SessionBasicInfo) o;
        return title.equals(that.title) && image.equals(that.image);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, image);
    }
} 