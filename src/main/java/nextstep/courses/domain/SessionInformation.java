package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

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

    public long price() {
        return price.price();
    }

    public SessionChargeType chargeType() {
        return price.type();
    }

    public LocalDate startDate() {
        return date.startDate();
    }

    public LocalDate endDate() {
        return date.endDate();
    }

    public byte[] coverImage() {
        return image.image();
    }

    public String title() {
        return title.title();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionInformation)) return false;
        SessionInformation that = (SessionInformation) o;
        return Objects.equals(image, that.image) && Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, title, price, date);
    }
}
