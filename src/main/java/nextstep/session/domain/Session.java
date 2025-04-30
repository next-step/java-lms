package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private String title;
    private CoverImage coverImage;
    private Duration duration;

    private PaymentPolicy paymentPolicy;
    private EnrolledStudents enrolledStudents;

    private SessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Session(Long id, String title, CoverImage coverImage, Duration duration,
            PaymentPolicy paymentPolicy, EnrolledStudents enrolledStudents,
            SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.duration = duration;
        this.paymentPolicy = paymentPolicy;
        this.enrolledStudents = enrolledStudents;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public String title() {
        return title;
    }

    public PaymentPolicy paymentPolicy() {
        return paymentPolicy;
    }

    public Payment enroll(NsUser nsUser, Long amount) {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }

        paymentPolicy.validateEnrollment(amount);

        enrolledStudents.add(paymentPolicy, nsUser);

        return new Payment("P1", 1L, nsUser.getId(), amount);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Duration getDuration() {
        return duration;
    }

    public PaymentPolicy getPaymentPolicy() {
        return paymentPolicy;
    }

    public EnrolledStudents getEnrolledStudents() {
        return enrolledStudents;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
