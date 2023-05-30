package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {
    private List<NsUser> nsUsers = new ArrayList<>();
    private int maxUserCount;

    public NsUsers(int maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public void enroll(NsUser user) {
        if (count() >= maxUserCount) {
            throw new IllegalArgumentException("강의 최대 수강 인원이 초과되었습니다.");
        }
        nsUsers.add(user);
    }

    public int count() {
        return nsUsers.size();
    }
}
