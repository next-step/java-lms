package nextstep.courses.domain.session.type;

import nextstep.courses.domain.registration.Registrations;

public interface SessionType {
    void validateEnroll(long payAmount);

    Registrations getRegistrations();
}