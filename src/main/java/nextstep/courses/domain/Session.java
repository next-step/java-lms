package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public abstract class Session {

    private Long id;

    private Image coverImage;

    private SessionType type;

    private Enrollment enrollment;

    public Session(Image image, SessionType type, Enrollment enrollment) {
        this.coverImage = image;
        this.type = type;
        this.enrollment = enrollment;
    }


    public Long getId() {
        return this.id;
    }

    public Image getImage() {
        return this.coverImage;
    }


    public SessionType getType() {
        return this.type;
    }


    public abstract void enroll(NsUser student, Payment payment);

    public Enrollment getEnrollment() {
        return this.enrollment;
    }
}
