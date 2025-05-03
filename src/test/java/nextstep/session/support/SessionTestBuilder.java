package nextstep.session.support;

import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImages;
import nextstep.session.domain.session.*;
import nextstep.session.domain.student.EnrolledStudents;

import java.time.LocalDate;

public class SessionTestBuilder {
    private Long id = 1L;
    private String title = "DefaultTitle";
    private CoverImages coverImages = new CoverImages();
    private Duration duration = new Duration(LocalDate.now(), LocalDate.now().plusDays(1));
    private PaymentPolicy paymentPolicy = new PaidPaymentPolicy(800_000, 10);
    private EnrolledStudents enrolledStudents = new EnrolledStudents();
    private SessionStatus sessionStatus = SessionStatus.PREPARING;
    private RecruitmentStatus recruitmentStatus = RecruitmentStatus.OPEN;
    private SessionType sessionType = SessionType.AUTO_APPROVAL;

    public SessionTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionTestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public SessionTestBuilder withCoverImages(CoverImages coverImages) {
        this.coverImages = coverImages;
        return this;
    }

    public SessionTestBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public SessionTestBuilder withPaymentPolicy(PaymentPolicy paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
        return this;
    }

    public SessionTestBuilder withEnrolledStudents(EnrolledStudents students) {
        this.enrolledStudents = students;
        return this;
    }

    public SessionTestBuilder withSessionStatus(SessionStatus status) {
        this.sessionStatus = status;
        return this;
    }

    public SessionTestBuilder withRecruitmentStatus(RecruitmentStatus status) {
        this.recruitmentStatus = status;
        return this;
    }

    public SessionTestBuilder withSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
        return this;
    }

    public Session build() {
        return new SessionBuilder()
                .id(id)
                .title(title)
                .coverImages(coverImages)
                .duration(duration)
                .paymentPolicy(paymentPolicy)
                .enrolledStudents(enrolledStudents)
                .sessionStatus(sessionStatus)
                .recruitmentStatus(recruitmentStatus)
                .sessionType(sessionType)
                .build();
    }
}
