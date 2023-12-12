package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession() {
        this(null, LocalDate.now(), null);
    }

    public FreeSession(CoverImage image, LocalDate startDate, LocalDate endDate) {
        super(image, true, startDate, endDate);
    }

    @Override
    public void enroll(Payment payment) throws Exception {
        super.enroll(payment);
    }
}
