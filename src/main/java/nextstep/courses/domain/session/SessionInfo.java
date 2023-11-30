package nextstep.courses.domain.session;

import org.springframework.util.Assert;

public class SessionInfo {
    private final String title;
    private final long price;

    public SessionInfo(String title, long price) {
        validate(title, price);

        this.title = title;
        this.price = price;
    }

    private void validate(final String title, final long price) {
        Assert.hasText(title, "title cannot be blank");
        Assert.isTrue(price >= 0, "price cannot be negative");
    }

    public long getPrice() {
        return this.price;
    }
}
