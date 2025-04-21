package nextstep.sessions.domain;

import nextstep.sessions.exception.AttendeeException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private Long courseId;

    private Long imageId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int maxAttendees;

    private int currentAttendees;

    private SessionType type;

    private SessionStatus status;

    private Long price;

    public Session(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.imageId = builder.imageId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.maxAttendees = builder.maxAttendees;
        this.currentAttendees = builder.currentAttendees;
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

        this.currentAttendees++;
        attendee.enroll(this.id);
    }

    private boolean isOpen() {
        return SessionStatus.OPEN.equals(this.status);
    }

    private boolean isNotOpen() {
        return !isOpen();
    }

    private boolean exceedMaxAttendees() {
        return isPaid() && currentAttendees >= maxAttendees;
    }

    private boolean isPaid() {
        return SessionType.PAID.equals(this.type);
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

    public int getCurrentAttendees() {
        return currentAttendees;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Long getPrice() {
        return price;
    }

    public static class Builder {
        private Long id;
        private Long courseId;
        private Long imageId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private int maxAttendees;
        private int currentAttendees;
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

        public Builder currentAttendees(int currentAttendees) {
            this.currentAttendees = currentAttendees;
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
