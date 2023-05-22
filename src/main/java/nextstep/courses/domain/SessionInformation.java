package nextstep.courses.domain;

public class SessionInformation {
    private final SessionCoverImage image;
    private final SessionName name;
    private final SessionDate date;

    public SessionInformation(SessionCoverImage image, SessionName name, SessionDate date) {
        this.image = image;
        this.name = name;
        this.date = date;
    }
}
