package nextstep.courses.domain.sessions;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.images.Image;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.Payments;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.sessions.SessionStatus.OPEN;
import static nextstep.courses.domain.sessions.SessionType.PAID;

public class Session {

    private Long id;

    private Course course;

    private Image image;

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

    public Session(Builder builder) {
        this.id = builder.id;
        this.course = builder.course;
        this.image = builder.image;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.maxAttendees = builder.maxAttendees;
        this.type = builder.type;
        this.status = builder.status;
        this.price = builder.price;
    }

    public Session(Long id, Course course, Image image, LocalDateTime startDate, LocalDateTime endDate,
                   int maxAttendees, SessionType type, SessionStatus status, Long price) {
        this.id = id;
        this.course = course;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxAttendees = maxAttendees;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public void addAttendee(NsUser attendee) {
        if (isNotOpen()) {
            throw new IllegalStateException("Session is not open for registration");
        }

        if (exceedMaxAttendees()) {
            throw new IllegalStateException("Maximum number of attendees reached");
        }

        if (payments.paidIncorrectly(attendee.getId(), this.price)) {
            throw new IllegalStateException("Payment not completed");
        }

        attendees.add(attendee);
    }

    private boolean isOpen() {
        return OPEN.equals(this.status);
    }

    private boolean isNotOpen() {
        return !isOpen();
    }

    private boolean exceedMaxAttendees() {
        return isPaid() && getAttendeesSize() >= maxAttendees;
    }

    private boolean isPaid() {
        return PAID.equals(this.type);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public int getAttendeesSize() {
        return attendees.size();
    }

    public static class Builder {
        private Long id;
        private Course course;
        private Image image;
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

        public Builder course(Course course) {
            this.course = course;
            return this;
        }

        public Builder image(Image image) {
            this.image = image;
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
