package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private String title;
    private CoverImage coverImage;
    private Duration duration;

    private PaymentPolicy paymentPolicy;
    private EnrolledStudents enrolledStudents;

    private SessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Session(
            String title,
            CoverImage coverImage,

            Duration duration,

            PaymentPolicy paymentPolicy,
            EnrolledStudents enrolledStudents,
            SessionStatus status,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.title = title;
        this.duration = duration;
        this.coverImage = coverImage;
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
