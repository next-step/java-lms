package nextstep.courses.domain.session;

import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {

    private List<NsUser> users = new ArrayList<>();

    public SessionUsers() {
    }

    public void addUser(NsUser nsUser, SessionType sessionType) {
        if (!sessionType.canRegisterNewUser(users.size())) {
            throw new ExceedMaxAttendanceCountException("이미 최대 수강 인원이 다 찼습니다.");
        }
        users.add(nsUser);
    }

    public int totalAttendUsersCount() {
        return users.size();
    }
}
