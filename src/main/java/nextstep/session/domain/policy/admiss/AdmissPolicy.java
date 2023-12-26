package nextstep.session.domain.policy.admiss;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

@FunctionalInterface
public interface AdmissPolicy<T extends Session> {
    void validate(T session, NsUser loginUser, NsUser student);
}
