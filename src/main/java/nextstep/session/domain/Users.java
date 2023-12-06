package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class Users {

    private final int numberOfMaximumMembers;

    private final Set<NsUser> value;

    public Users(int numberOfMaximumMembers, Set<NsUser> nsUsers) {
        this.numberOfMaximumMembers = numberOfMaximumMembers;
        this.value = new HashSet<>(nsUsers);
    }

    private boolean canRegister(SessionType sessionType) {
        if (sessionType == SessionType.FREE) {
            return true;
        }

        return value.size() < numberOfMaximumMembers;
    }

    public void register(NsUser user, SessionType sessionType) {
        if (!canRegister(sessionType)) {
            throw new IllegalStateException("최대 수강 인원을 초과하였습니다.");
        }

        value.add(user);
    }
}
