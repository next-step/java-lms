package nextstep.courses.domain;

public class SessionWithImage {

    private final Session session;
    private final ImageFile image;

    public SessionWithImage(Session session, ImageFile image) {
        this.session = session;
        this.image = image;
    }
}
