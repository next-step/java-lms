package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private CardinalNumber cardinalNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    private Image image;

    public Session() {
        this.image = new Image();
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
}
