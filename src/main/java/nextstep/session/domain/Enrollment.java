package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private final SessionPricing sessionPricing;
    private final Participants participants;

    public Enrollment(SessionPricing sessionPricing, Participants participants) {
        this.sessionPricing = sessionPricing;
        this.participants = participants;
    }

    public Participants getParticipants() {
        return participants;
    }

    public void enrollStudent(final NsUser nsUser, final Payment payment) {
        if (sessionPricing.isFreeSession()) {
            throw new IllegalArgumentException("이 강의는 무료 강의입니다. 결제 정보가 필요하지 않습니다.");
        }
        sessionPricing.canEnroll(payment, participants.count());
        participants.addStudent(nsUser);
    }

    public void enrollStudent(final NsUser nsUser) {
        System.out.println(!sessionPricing.isFreeSession());
        System.out.println(sessionPricing);
        if (!sessionPricing.isFreeSession()) {
            throw new IllegalArgumentException("이 강의는 유료 강의입니다. 결제 금액이 필요 합니다.");
        }
        participants.addStudent(nsUser);
    }

}
