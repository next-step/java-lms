package nextstep.session.domain;

import static nextstep.session.domain.PaymentType.COMPLETED;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.image.domain.Image;
import nextstep.users.domain.NsUser;

public class Session {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Image image;

    private SessionType type;

    private SessionStatus status;

    private SessionParticipants participants;

    private List<SessionPayment> payments = new ArrayList<>();

    private long price;

    public void enrollSession(long fee, NsUser user) {
        if (type.isPaid()) {
            enrollPaidSession(fee, user);
        }
        enrollFreeSession(user);
    }

    private void enrollPaidSession(long fee, NsUser user) {
        if (!participants.isFull()) {
            paymentSession(fee, user);
        }
    }

    private void paymentSession(long fee, NsUser user) {
        if (this.price == fee) {
            enrollParticipant(user);
        }
    }

    private void enrollParticipant(NsUser user) {
        participants.enroll(user);
        this.payments.add(new SessionPayment(user, COMPLETED));
    }

    private void enrollFreeSession(NsUser user) {
        enrollParticipant(user);
    }
}
