package nextstep.courses.domain.session;

import org.springframework.util.Assert;

public class SessionInfo {
    private final String title;
    private final Long price;
    private final ChargeStatus chargeStatus;

    public SessionInfo(final String title, final Long price) {
        validate(title, price);

        this.title = title;
        this.price = price;
        this.chargeStatus = ChargeStatus.decide(price);
    }

    private void validate(final String title, final Long price) {
        Assert.hasText(title, "title cannot be blank");
        Assert.isTrue(price != null && price >= 0, "price cannot be negative");
    }

    public long getPrice() {
        return this.price;
    }

    public boolean isPaidSession() {
        return this.chargeStatus.isPaid();
    }
}
