package nextstep.courses.domain.registration;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class Students {
    private final int maxUserCount;
    private final Set<NsUser> users;

    public Students() {
        this(0);
    }

    public Students(int maxUserCount) {
        this(maxUserCount, new HashSet<>());
    }

    public Students(int maxUserCount, Set<NsUser> users) {
        this.maxUserCount = maxUserCount;
        this.users = users;
    }

    public void add(NsUser nsUser) {
        validateUserCount();
        validateUserDuplicated(nsUser);
        users.add(nsUser);
    }

    private void validateUserCount() {
        if (this.maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
    }

    private void validateUserDuplicated(NsUser user) {
        if (!users.add(user)) {
            throw new IllegalArgumentException("이미 등록 되었습니다.");
        }
    }

    public Set<NsUser> getUsers() {
        return this.users;
    }

    public int getMaxUserCount() {
        return this.maxUserCount;
    }
}
