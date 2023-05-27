package nextstep.courses.domain;

public class SessionInformation {
    private final SessionCoverImage image;
    private final SessionTitle title;
    private final SessionPrice price;
    private final SessionDate date;

    public SessionInformation(SessionCoverImage image, SessionTitle title, SessionPrice price, SessionDate date) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.date = date;
    }

    public String title() {
        return title.title();
    }
}
