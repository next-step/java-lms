package nextstep.courses.domain;

public class SessionBasicInfo {
    private final String title;
    private final SessionStatus status;
    private final SessionImage image;

    public SessionBasicInfo(String title, SessionStatus status, SessionImage image) {
        this.title = title;
        this.status = status;
        this.image = image;
    }

    public boolean isRecruiting() {
        return status.isRecruiting();
    }

    public boolean isImageValid() {
        return image != null && image.isValid();
    }

    public boolean hasTitle(String title) {
        return this.title.equals(title);
    }

    public boolean hasStatus(SessionStatus status) {
        return this.status == status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionBasicInfo that = (SessionBasicInfo) o;
        return title.equals(that.title) && status == that.status && image.equals(that.image);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, status, image);
    }
} 