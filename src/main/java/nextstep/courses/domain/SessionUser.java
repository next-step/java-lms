package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionUser {
    private final int maxEnrollment;
    private final List<NsUser> nsUsers;
    private static final String ALERT_TEXT = "최대 수강 인원을 초과할 수 없습니다.";

    public SessionUser(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
        this.nsUsers = new ArrayList<>();
    }

    public int getUserCount(){
        return nsUsers.size();
    }

    public void addEnroll(NsUser nsUser) {
        canEnrollment();
        nsUsers.add(nsUser);
    }

    public void canEnrollment() {
        if (nsUsers.size() >= maxEnrollment) {
            throw new IllegalArgumentException(ALERT_TEXT);
        }
    }
}
