package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.Objects;


public abstract class Session {
    protected LocalDateTime startDate;

    protected LocalDateTime endDate;

    protected CoverImage coverImage;

    protected Long price;

    protected SessionStatus status;

    protected int numberOfApplicants;

    public Session(
            LocalDateTime startDate,
            LocalDateTime endDate,
            CoverImage coverImage,
            Long price,
            SessionStatus status,
            int numberOfApplicants
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.price = price;
        this.status = status;
        this.numberOfApplicants = numberOfApplicants;
    }

    public Session(Long price, SessionStatus status) {
        this.price = price;
        this.status = status;
    }

    public void register(Payment payment) {
        if (!payment.hasAmount(price)) {
            throw new CannotRegisterSessionException("결제 금액과 수강료가 일치하지 않습니다.");
        }

        this.register();
    }

    protected abstract void register();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return numberOfApplicants == session.numberOfApplicants && Objects.equals(startDate, session.startDate) &&
               Objects.equals(endDate, session.endDate) && Objects.equals(coverImage, session.coverImage) &&
               Objects.equals(price, session.price) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, coverImage, price, status, numberOfApplicants);
    }
}
