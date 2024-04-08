package nextstep.sessions.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.users.domain.NsUser;

public class Session {

    private Long id;

    private int maxNumber;

    private Status status;

    private List<NsUser> attendees = new ArrayList<>();

    public Session(final int maxNumber, final Status status) {
        this.maxNumber = maxNumber;
        this.status = status;
    }

    public void addAttendee(final NsUser user) {
        validateAttendeeNumber();
        validateRecruitingStatus();
        attendees.add(user);
    }

    private void validateAttendeeNumber() {
        if (attendees.size() >= maxNumber) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
    }

    private void validateRecruitingStatus() {
        if (status != Status.RECRUITING) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    public List<NsUser> getAttendees() {
        return attendees;
    }
}
