package nextstep.courses.domain;

import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {

    private List<NsUser> users = new ArrayList<>();
    private boolean isFree;
    private Integer maxAttendance;

    public SessionUsers(boolean isFree, Integer maxAttendance) {
        this.isFree = isFree;
        if (isFree) {
            this.maxAttendance = null;
            return;
        }
        this.maxAttendance = maxAttendance;
    }

    public void addUser(NsUser nsUser) {
        if (!isFreeSession() && users.size() >= maxAttendance) {
            throw new ExceedMaxAttendanceCountException("이미 최대 수강 인원이 다 찼습니다.");
        }
        users.add(nsUser);
    }

    public int totalAttendUsersCount() {
        return users.size();
    }

    private boolean isFreeSession() {
        return isFree && maxAttendance == null;
    }
}
