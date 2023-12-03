package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession(long id, CoverImage image, LocalDate start, LocalDate end, SessionState state) {
        super(id, image, start, end, state);
    }

    @Override
    public void apply(Payment payment) {
        validateState();
        this.participants = participants.add(payment.findUser());
    }

}
