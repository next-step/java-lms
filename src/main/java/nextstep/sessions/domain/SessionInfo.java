package nextstep.sessions.domain;

import nextstep.image.domain.Image;

public class SessionInfo {

    private final String title;
    private final Long creatorId;
    private final Image image;

    public SessionInfo(String title, Long creatorId, Image image) {
        this.title = title;
        this.creatorId = creatorId;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Image getImage() {
        return image;
    }
}
