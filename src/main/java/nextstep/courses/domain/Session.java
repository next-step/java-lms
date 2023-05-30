package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private CardinalNumber cardinalNumber;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    private Image image;

    public Session() {
        this.image = new Image();
    }

    public Session(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Session(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public Session(int cardinalNumber) {
        this.cardinalNumber = new CardinalNumber(cardinalNumber);
    }
    public Session(CardinalNumber cardinalNumber, LocalDate startDate, LocalDate endDate) {
        this.cardinalNumber = cardinalNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    boolean isStartDateSame(LocalDate localDate) {
        return startDate == localDate;
    }

    boolean isEndDateSame(LocalDate localDate) {
        return endDate == localDate;
    }

    public void updateImageWithUrl(String imgUrl) {
        image.updateUrl(imgUrl);
    }

    public boolean isSameCoverImage(String imgUrl) {
        return image.isSameImage(imgUrl);
    }

    public boolean isFree() {
        return sessionType.isFree();
    }

    public boolean isReady() {
        return sessionStatus.isReady();
    }

    public boolean isOpen() {
        return sessionStatus.isOpen();
    }

    public boolean isClosed() {
        return sessionStatus.isClosed();
    }
}
