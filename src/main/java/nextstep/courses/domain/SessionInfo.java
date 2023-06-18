package nextstep.courses.domain;

public class SessionInfo {
    private Long id;
    private final String title;
    private final String imageUrl;

    public SessionInfo(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
