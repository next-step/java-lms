package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private String title;
    private CoverImage coverImage;
    private Duration duration;

    private PaymentPolicy paymentPolicy;
    private EnrolledStudents enrolledStudents;

    private SessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static class Builder {
        private String title;
        private CoverImage coverImage;
        private Duration duration;

        private PaymentPolicy paymentPolicy;
        private EnrolledStudents enrolledStudents;

        private SessionStatus status;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder coverImage(CoverImage coverImage) {
            this.coverImage = coverImage;
            return this;
        }
        public Builder duration(Duration duration) {
            this.duration = duration;
            return this;
        }
        public Builder paymentPolicy(PaymentPolicy paymentPolicy) {
            this.paymentPolicy = paymentPolicy;
            return this;
        }
        public Builder enrolledStudents(EnrolledStudents enrolledStudents) {
            this.enrolledStudents = enrolledStudents;
            return this;
        }
        public Builder status(SessionStatus status) {
            this.status = status;
            return this;
        }
        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder updatedAt(LocalDateTime updatedAt) {
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

            return new Session(this);
        }
    }

    private Session(Builder builder) {
        this.title = builder.title;
        this.duration = builder.duration;
        this.coverImage = builder.coverImage;
        this.paymentPolicy = builder.paymentPolicy;
        this.enrolledStudents = builder.enrolledStudents;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public String title() {
        return title;
    }

    public PaymentPolicy paymentPolicy() {
        return paymentPolicy;
    }

    public Payment enroll(NsUser nsUser, Long amount) {
        validateEnrollment();
        paymentPolicy.validateEnrollment(amount);

        enrolledStudents.add(nsUser);

        return new Payment("P1", 1L, nsUser.getId(), amount);
    }
    void validateEnrollment() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }
        if (enrolledStudents.count() >= paymentPolicy.enrollmentLimit()) {
            throw new IllegalStateException("수강 최대 인원을 초과했습니다.");
        }
    }
}
