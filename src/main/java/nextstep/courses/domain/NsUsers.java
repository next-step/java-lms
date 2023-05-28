package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {
    List<NsUser> nsUsers = new ArrayList<>();
    int maxUserCount;

    public NsUsers(int maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public void enroll(NsUser user) {
        validateMaxUserCount();
        nsUsers.add(user);
    }

    private void validateMaxUserCount() {
        if (nsUsers.size() == maxUserCount) {
            throw new IllegalArgumentException("강의 최대 수강 인원이 초과되었습니다.");
        }
    }

    public int count() {
        return nsUsers.size();
    }
}
