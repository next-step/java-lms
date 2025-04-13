package nextstep.courses.domain.session.info.basic;

public class SessionBasicInfo {
    private final String title;
    private final SessionThumbnail thumbnail;

    public SessionBasicInfo(String title, SessionThumbnail thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public boolean hasTitle(String title) {
        return this.title.equals(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionBasicInfo that = (SessionBasicInfo) o;
        return title.equals(that.title) && thumbnail.equals(that.thumbnail);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, thumbnail);
    }
} 