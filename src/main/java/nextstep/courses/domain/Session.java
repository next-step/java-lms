package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;

public class Session {
    private final Long id;
    private final Long courseId;
    private final SessionInformation information;
    private final SessionStatus status;
    private final SessionAttendees attendees;

    public Session(Long id, Long courseId, SessionInformation information, SessionStatus status, SessionAttendees attendees) {
        this.id = id;
        this.courseId = courseId;
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

    public SessionStatus status() {
        return status;
    }

    public long price() {
        return information.price();
    }

    public SessionChargeType chargeType() {
        return information.chargeType();
    }

    public LocalDate startDate() {
        return information.startDate();
    }

    public LocalDate endDate() {
        return information.endDate();
    }

    public int maxNumberOfAttendees() {
        return attendees.maxNumberOfAttendees();
    }

    public byte[] coverImage() {
        return information.coverImage();
    }

    public long id() {
        return id;
    }

    public long courseId() {
        return courseId;
    }

    private void validateStatus() {
        if(!status.canRecruit()) {
            throw new IllegalArgumentException("강의는 모집중일 때 신청 가능합니다: " + status);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(information, session.information) && status == session.status && Objects.equals(attendees, session.attendees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, information, status, attendees);
    }
}
