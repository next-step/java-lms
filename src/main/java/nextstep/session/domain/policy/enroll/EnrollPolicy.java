package nextstep.session.domain.policy.enroll;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

@FunctionalInterface
public interface EnrollPolicy<T extends Session> {
    void validate(T session, NsUser nsUser);
}
