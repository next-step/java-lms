package nextstep.courses.domain.sessions;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.sessions.SessionType.PAID;

public class Session {

    private Long id;

    private Long courseId;

    private Long imageId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int maxAttendees;

    private SessionType type;

    private SessionStatus status;

    private Long price;

    private List<NsUser> attendees = new ArrayList<>();

    private List<Payment> payments = new ArrayList<>();

    public Session() {
    }

    public Session(Long id, Long courseId, Long imageId, LocalDateTime startDate, LocalDateTime endDate,
                   int maxAttendees, SessionType type, SessionStatus status, Long price) {
        this.id = id;
        this.courseId = courseId;
        this.imageId = imageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxAttendees = maxAttendees;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public void addAttendee(NsUser attendee) {
        if (exceedMaxAttendees()) {
            throw new IllegalStateException("Maximum number of attendees reached");
        }
        attendees.add(attendee);
    }

    private boolean exceedMaxAttendees() {
        return isPaid() && attendees.size() >= maxAttendees;
    }

    private boolean isPaid() {
        return PAID.equals(this.type);
    }
}
