package nextstep.courses.domain;

public class SessionInfo {
    private Long id;
    private final String title;
    private final Long creatorId;
    private final String imageUrl;

    public SessionInfo(String title, Long creatorId, String imageUrl) {
        this.title = title;
        this.creatorId = creatorId;
        this.imageUrl = imageUrl;
    }
}
