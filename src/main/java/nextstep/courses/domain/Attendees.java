package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Attendees {

    private List<NsUser> attendees;

    public Attendees() {
        this.attendees = new ArrayList<>();
    }

    public void add(NsUser user) {
        attendees.add(user);
    }
}
