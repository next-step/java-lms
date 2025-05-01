package nextstep.session.domain.session;

import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImages;
import nextstep.session.domain.student.EnrolledStudents;

import java.time.LocalDateTime;

public class SessionBuilder {
    private Long id;
    private String title;
    private CoverImages coverImages;
    private Duration duration;
    private PaymentPolicy paymentPolicy;
    private EnrolledStudents enrolledStudents;
    private SessionStatus sessionStatus;
    private RecruitmentStatus recruitmentStatus;
    private SessionType sessionType;
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

    public SessionBuilder coverImages(CoverImages coverImages) {
        this.coverImages = coverImages;
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

    public SessionBuilder sessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }
    public SessionBuilder recruitmentStatus(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
        return this;
    }
    public SessionBuilder sessionType(SessionType sessionType) {
        this.sessionType = sessionType;
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
                id, title, coverImages,
                duration, paymentPolicy,
                enrolledStudents, sessionStatus, recruitmentStatus, sessionType,
                createdAt, updatedAt
        );
    }
}
