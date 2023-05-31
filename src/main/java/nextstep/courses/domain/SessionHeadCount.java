package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;
import nextstep.users.domain.NsUser;

public class SessionHeadCount {

    private final int maxCount;
    private final Set<NsUser> nsUsers;

    public SessionHeadCount(int maxCount) {
        this.maxCount = maxCount;
        nsUsers = new HashSet<>();
    }

    public void addPerson(NsUser nsUser) {
        int curCount = nsUsers.size();
        nsUsers.add(nsUser);

        if (nsUsers.size() > maxCount) {
            throw new IllegalArgumentException("수강신청 정원을 넘었습니다.");
        }

        if (nsUsers.size() == curCount) {
            throw new IllegalArgumentException("중복 신청입니다.");
        }
    }
}
