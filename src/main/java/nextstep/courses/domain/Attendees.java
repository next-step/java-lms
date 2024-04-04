package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class Attendees {

    private Set<NsUser> attendees;

    public Attendees() {
        this.attendees = new HashSet<>();
    }

    public void add(NsUser user) {
        if (attendees.contains(user)) {
            throw new IllegalStateException("이미 등록된 유저 입니다");
        }
        attendees.add(user);
    }
}
