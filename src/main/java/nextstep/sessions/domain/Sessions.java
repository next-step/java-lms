package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {

    private List<Session> values = new ArrayList<>();

    public Sessions() {
        values = new ArrayList<>();
    }

    public Sessions(List<Session> sessions) {
        for (Session session : sessions) {
            addSession(session);
        }
    }

    public List<Session> getSessions() {
        return Collections.unmodifiableList(values);
    }

    public void addSession(Session session) {
        session.checkSessionStatus();
        values.add(session);
    }

    private long totalPrice() {
        return values.stream()
                .mapToLong(session -> (long) session.getCharge().getPrice())
                .sum();
    }

    public void addStudent() {
        values.stream()
                .forEach(session -> session.addStudent());
    }
}
