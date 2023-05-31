package nextstep.sessions.domain;

public class CoverImage {

    private final Long id;
    private final String fileName;
    private final String url;

    public CoverImage(Long id, String fileName, String url) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
    }
}
