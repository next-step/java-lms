package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private int maxNumber;
    private List<NsUser> attendees = new ArrayList<>();

    public Session(final int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void addAttendee(final NsUser user) {
        validateAttendeeNumber();
        attendees.add(user);
    }

    private void validateAttendeeNumber() {
        if (attendees.size() >= maxNumber) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
    }
}
