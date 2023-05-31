package nextstep.courses.domain.session;

public class SessionImage {

    private String name;

    private String path;

    public SessionImage(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
