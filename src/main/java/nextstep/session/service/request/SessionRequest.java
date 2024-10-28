package nextstep.session.service.request;

import nextstep.session.domain.PaymentType;
import nextstep.session.domain.Session;
import nextstep.session.domain.image.Image;

import java.time.LocalDateTime;

public class SessionRequest {
    private final String title;
    private ImageRequest imageRequest;
    private final PaymentType paymentType;
    private int subscribeMax;
    private int price;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionRequest(String title, PaymentType paymentType, LocalDateTime startDate, LocalDateTime endDate, int price, int subscribeMax, ImageRequest imageRequest) {
        this.title = title;
        this.paymentType = paymentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.subscribeMax = subscribeMax;
        this.imageRequest = imageRequest;
    }

    public Session toDomain() {
        return new Session(title, imageRequest.toDomain(), paymentType, subscribeMax, price, startDate, endDate);
    }
}
