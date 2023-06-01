package nextstep.courses.domain.session;

public class SessionCoverImage {

    private final Long id;

    private final String path;

    private final String name;

    public SessionCoverImage(Long id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
