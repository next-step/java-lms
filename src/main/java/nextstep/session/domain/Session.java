package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;

import java.time.LocalDateTime;

public class Session {

    private String title;
    private CoverImage coverImage;
    private Duration duration;

    private PaymentPolicy paymentPolicy;
    private Integer enrolledStudentsCount;

    private SessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Session(
            String title,
            CoverImage coverImage,

            Duration duration,

            PaymentPolicy paymentPolicy,
            int enrolledStudentsCount,
            SessionStatus status,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.title = title;
        this.duration = duration;
        this.coverImage = coverImage;
        this.paymentPolicy = paymentPolicy;
        this.enrolledStudentsCount = enrolledStudentsCount;
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

    public Payment enroll(Long nsUserId, Long amount) {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }

        paymentPolicy.validateEnrollment(enrolledStudentsCount, amount);

        enrolledStudentsCount++;
        return new Payment("1", 1L, nsUserId, amount);
    }
}
