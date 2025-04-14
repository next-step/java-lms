package nextstep.session.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {

    public enum Status {
        PREPARING, RECRUITING, CLOSED
    }

    private Long id;
    private Long courseId;

    private String title;
    private CoverImage coverImage;
    private Duration duration;

    private PaymentPolicy paymentPolicy;
    private Integer enrolledStudentsCount;

    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Session(
            Long id,
            Long courseId,

            String title,
            CoverImage coverImage,

            Duration duration,

            PaymentPolicy paymentPolicy,
            int enrolledStudentsCount,
            Status status,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.courseId = courseId;
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
        if (status != Status.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }

        paymentPolicy.validateEnrollment(enrolledStudentsCount, amount);

        enrolledStudentsCount++;
        return new Payment("1", id, nsUserId, amount);
    }
}
