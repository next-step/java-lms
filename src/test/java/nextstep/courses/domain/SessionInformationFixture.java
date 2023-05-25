package nextstep.courses.domain;

public enum SessionInformationFixture {
    TDD_SESSION_INFORMATION(
            SessionCoverImageFixture.TDD_SESSION_COVER_IMAGE.sessionCoverImage(),
            SessionName.of("TDD"),
            SessionPriceFixture.TDD_SESSION_PRICE.sessionPrice(),
            SessionDateFixture.TDD_SESSION_DATE.sessionDate()
    );

    private final SessionCoverImage image;
    private final SessionName name;
    private final SessionPrice price;
    private final SessionDate date;

    SessionInformationFixture(SessionCoverImage image, SessionName name, SessionPrice price, SessionDate date) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public SessionInformation sessionInformation() {
        return new SessionInformation(image, name, price, date);
    }
}
