package nextstep.courses.domain;

public enum SessionInformationFixture {
    TDD_SESSION_INFORMATION(
            SessionCoverImageFixture.TDD_SESSION_COVER_IMAGE.sessionCoverImage(),
            SessionTitle.of("TDD"),
            SessionPriceFixture.TDD_SESSION_PRICE.sessionPrice(),
            SessionDateFixture.TDD_SESSION_DATE.sessionDate()
    );

    private final SessionCoverImage image;
    private final SessionTitle title;
    private final SessionPrice price;
    private final SessionDate date;

    SessionInformationFixture(SessionCoverImage image, SessionTitle title, SessionPrice price, SessionDate date) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.date = date;
    }

    public SessionInformation sessionInformation() {
        return new SessionInformation(image, title, price, date);
    }
}
