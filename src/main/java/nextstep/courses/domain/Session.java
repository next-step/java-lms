package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private final Long id;
    private final SessionInformation information;
    private final SessionStatus status;
    private final SessionAttendees attendees;

    public Session(Long id, SessionInformation information, SessionStatus status, SessionAttendees attendees) {
        this.id = id;
        this.information = information;
        this.status = status;
        this.attendees = attendees;
    }

    public void apply(NsUser user) {
        validateStatus();
        attendees.add(user);
    }

    public String title() {
        return information.title();
    }

    private void validateStatus() {
        if(!status.canRecruit()) {
            throw new IllegalArgumentException("강의는 모집중일 때 신청 가능합니다: " + status);
        }
    }
}
