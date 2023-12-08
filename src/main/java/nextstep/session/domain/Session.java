package nextstep.session.domain;

import static nextstep.session.domain.PaymentType.COMPLETED;

import java.util.ArrayList;
import java.util.List;
import nextstep.image.domain.Image;
import nextstep.session.exception.SessionOutOfDateException;
import nextstep.users.domain.NsUser;

public class Session {

    public static final String SESSION_OUT_OF_DATE_EXCEPTION = "강의가 시작되어 수강 신청할 수 없습니다.";

    private SessionPeriod sessionPeriod;

    private Image image;

    private SessionType type;

    private SessionStatus status;

    private SessionParticipants participants;

    private List<SessionPayment> payments = new ArrayList<>();

    private long price;

    public void enrollSession(long fee, NsUser user) {
        checkSessionIsStart();
        if (type.isPaid()) {
            enrollPaidSession(fee, user);
        }
        enrollFreeSession(user);
    }

    private void checkSessionIsStart() {
        if (sessionPeriod.isStart(new CurrentPeriodStrategy())) {
            throw new SessionOutOfDateException(SESSION_OUT_OF_DATE_EXCEPTION);
        }
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
