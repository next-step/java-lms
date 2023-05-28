package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private Image coverImage;
    private PaymentType paymentType;
    private SessionState state;
    private LocalDate startDate;
    private LocalDate endDate;

    public Session(Image coverImage, PaymentType paymentType, SessionState state, LocalDate startDate, LocalDate endDate) {
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
