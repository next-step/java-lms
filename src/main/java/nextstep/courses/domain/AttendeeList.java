package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class AttendeeList {
    private final List<NsUser> attendees;

    public AttendeeList() {
        this.attendees = new ArrayList<>();
    }

    public void add(NsUser attendee) {
        attendees.add(attendee);
    }    

    public Long size() {
        return (long) attendees.size();
    }
}
