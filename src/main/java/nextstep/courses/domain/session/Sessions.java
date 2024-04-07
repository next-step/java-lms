package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions = new ArrayList<>();

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Session findBy(Long sessionIdx) {
        return sessions.stream()
                .filter(item -> item.idx == sessionIdx)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));
    }

    public void add(PaidSession paidSession) {
        this.sessions.add(paidSession);
    }

    public boolean isFreeSession(Long sessionIdx) {
        Session session = findBy(sessionIdx);
        return session.isFreeSession();
    }

    public Payment toPayment(NsUser nsUser, Long sessionIdx) {
        Session session = findBy(sessionIdx);
        return session.toPayment(nsUser);
    }
}
