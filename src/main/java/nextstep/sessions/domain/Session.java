package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import nextstep.sessions.exception.AttendeeException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private Payments payments = new Payments();

    public Session() {
    }

    public Session(Long id, Long courseId, Long imageId, LocalDateTime startDate, LocalDateTime endDate,
                   int maxAttendees, SessionType type, SessionStatus status) {
        this.id = id;
        this.courseId = courseId;
        this.imageId = imageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxAttendees = maxAttendees;
        this.type = type;
        this.status = status;
    }

    public Session(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.imageId = builder.imageId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.maxAttendees = builder.maxAttendees;
        this.type = builder.type;
        this.status = builder.status;
        this.price = builder.price;
    }

    public void addAttendee(NsUser attendee) {
        if (isNotOpen()) {
            throw new AttendeeException("Session is not open for registration");
        }

        if (exceedMaxAttendees()) {
            throw new AttendeeException("Maximum number of attendees reached");
        }

        if (payments.paidIncorrectly(attendee.getId(), this.price)) {
            throw new AttendeeException("Payment not completed");
        }

        attendees.add(attendee);
    }

    private boolean isOpen() {
        return SessionStatus.OPEN.equals(this.status);
    }

    private boolean isNotOpen() {
        return !isOpen();
    }

    private boolean exceedMaxAttendees() {
        return isPaid() && getAttendeesSize() >= maxAttendees;
    }

    private boolean isPaid() {
        return SessionType.PAID.equals(this.type);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public int getAttendeesSize() {
        return attendees.size();
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getImageId() {
        return imageId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public static class Builder {
        private Long id;
        private Long courseId;
        private Long imageId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private int maxAttendees;
        private SessionType type;
        private SessionStatus status;
        private Long price;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder courseId(Long courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder imageId(Long imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder maxAttendees(int maxAttendees) {
            this.maxAttendees = maxAttendees;
            return this;
        }

        public Builder type(SessionType type) {
            this.type = type;
            return this;
        }

        public Builder status(SessionStatus status) {
            this.status = status;
            return this;
        }

        public Builder price(Long price) {
            this.price = price;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}
