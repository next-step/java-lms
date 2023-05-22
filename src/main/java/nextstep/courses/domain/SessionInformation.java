package nextstep.courses.domain;

public class SessionInformation {
    private final SessionCoverImage image;
    private final SessionName name;
    private final SessionPrice price;
    private final SessionDate date;

    public SessionInformation(SessionCoverImage image, SessionName name, SessionPrice price, SessionDate date) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
