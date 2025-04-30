package nextstep.session.domain;

import nextstep.payments.domain.PaymentPolicy;

import java.time.LocalDateTime;

public class SessionBuilder {
    private Long id;
    private String title;
    private CoverImage coverImage;
    private Duration duration;
    private PaymentPolicy paymentPolicy;
    private EnrolledStudents enrolledStudents;
    private SessionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SessionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SessionBuilder coverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public SessionBuilder duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public SessionBuilder paymentPolicy(PaymentPolicy paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
        return this;
    }

    public SessionBuilder enrolledStudents(EnrolledStudents enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
        return this;
    }

    public SessionBuilder status(SessionStatus status) {
        this.status = status;
        return this;
    }

    public SessionBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SessionBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Session build() {
        if (this.enrolledStudents == null) {
            this.enrolledStudents = new EnrolledStudents();
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }

        return new Session(
                id, title, coverImage, duration, paymentPolicy,
                enrolledStudents, status, createdAt, updatedAt
        );
    }
}
