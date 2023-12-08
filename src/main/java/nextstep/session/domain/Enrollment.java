package nextstep.session.domain;

import static nextstep.session.domain.PaymentType.COMPLETED;

import java.util.ArrayList;
import java.util.List;
import nextstep.session.exception.SessionFullOfParticipantsException;
import nextstep.users.domain.NsUser;

public class Enrollment {

    public static final String SESSION_FULL_OF_PARTICIPANTS_EXCEPTION = "강의 수강 인원이 모두 채워져 수강 신청을 할 수 없습니다.";

    private SessionParticipants participants = new SessionParticipants();

    private List<SessionPayment> payments = new ArrayList<>();

    private long price;

    public Enrollment(SessionParticipants participants, List<SessionPayment> payments, long price) {
        this.participants = participants;
        this.payments = payments;
        this.price = price;
    }

    public void enrollPaidSession(long fee, NsUser user) {
        validateSessionIsFull();
        paymentSession(fee, user);
    }

    private void validateSessionIsFull() {
        if (participants.isFull()) {
            throw new SessionFullOfParticipantsException(SESSION_FULL_OF_PARTICIPANTS_EXCEPTION);
        }
    }

    private void paymentSession(long fee, NsUser user) {
        if (this.price == fee) {
            enrollParticipant(user);
        }
    }

    private void enrollParticipant(NsUser user) {
        this.participants.enroll(user);
        this.payments = new ArrayList<>(payments);
        this.payments.add(new SessionPayment(user, COMPLETED));
    }

    public void enrollFreeSession(NsUser user) {
        enrollParticipant(user);
    }

    public SessionParticipants getParticipants() {
        return participants;
    }

    public List<SessionPayment> getPayments() {
        return payments;
    }
}
