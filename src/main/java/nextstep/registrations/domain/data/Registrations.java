package nextstep.registrations.domain.data;

import java.util.ArrayList;
import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.registrations.domain.exception.RegistrationException;
import nextstep.sessions.domain.data.Session;
import nextstep.users.domain.NsUser;

public class Registrations {

    List<Registration> registrations;

    public Registrations(List<Registration> registrations) {
        this.registrations = new ArrayList<>(registrations);
    }

    public void validateRegistration() {
        Session session = registrations.get(0).session();
        if (session.isPaid() && !isEnoughCapacity(session)) {
            throw new RegistrationException("강의 최대 인원을 초과했습니다.");
        }
    }

    private boolean isEnoughCapacity(Session session) {
        return registrations.size() < session.capacity();
    }

    public Registration newRegistration(NsUser user, Payment payment) {
        return new Registration(registrations.get(0).session(), user, payment);
    }
}
