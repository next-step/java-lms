package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionAttendees {
    private final int maxNumberOfAttendees;
    private final Set<NsUser> attendees = new HashSet<>();

    public SessionAttendees(int maxNumberOfAttendees) {
        this.maxNumberOfAttendees = maxNumberOfAttendees;
    }

    public void add(NsUser user) {
        validateDuplicateApply(user);
        validateMaxNumberOfAttendees();

        attendees.add(user);
    }

    private void validateMaxNumberOfAttendees() {
        if(attendees.size() >= maxNumberOfAttendees) {
            throw new IllegalArgumentException("수강 가능 인원이 다 찼습니다: " + maxNumberOfAttendees);
        }
    }

    private void validateDuplicateApply(NsUser user) {
        if(attendees.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 수강생입니다: " + user.getName());
        }
    }
}
