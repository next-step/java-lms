package nextstep.session.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private Long id;

    private String title;
    private List<CoverImage> coverImages;
    private Duration duration;

    private PaymentPolicy paymentPolicy;
    private EnrolledStudents enrolledStudents;

    private SessionStatus sessionStatus;
    private RecruitmentStatus recruitmentStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Session(Long id, String title, List<CoverImage> coverImages,
            Duration duration,
            PaymentPolicy paymentPolicy, EnrolledStudents enrolledStudents,
            SessionStatus sessionStatus, RecruitmentStatus recruitmentStatus,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.coverImages = coverImages;
        this.duration = duration;
        this.paymentPolicy = paymentPolicy;
        this.enrolledStudents = enrolledStudents;
        this.sessionStatus = sessionStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public String title() {
        return title;
    }

    // TODO : 정책관련해서 session과 paymentPolicy 양쪽에서 관리하는게 맞는지 고민해봐야함
    public Payment enroll2(NsUser nsUser, Long amount) {
        validateSessionEnrollmentPolicy();
        paymentPolicy.validateEnrollment(amount);

        enrolledStudents.checkPolicyAndAdd(paymentPolicy, nsUser);

        return new Payment("P1", 1L, nsUser.getId(), amount);
    }

    private void validateSessionEnrollmentPolicy() {
        if (recruitmentStatus != RecruitmentStatus.OPEN) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }
        if (sessionStatus != SessionStatus.PREPARING && sessionStatus != SessionStatus.IN_PROGRESS) {
            throw new IllegalStateException("준비중이거나 진행중인 강의만 수강 신청이 가능합니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<CoverImage> getCoverImages() {
        return coverImages;
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

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
