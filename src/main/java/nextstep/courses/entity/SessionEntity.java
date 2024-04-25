package nextstep.courses.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.SessionEnrollment;
import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.factory.EnrollmentCountFactory;
import nextstep.payments.domain.Money;

public class SessionEntity extends BaseEntity {

    private Long id;

    private String sessionName;

    private int registrationCount;

    private int maxRegistrationCount;

    private int tuitionFee;

    private Long imageId;

    private String recruitmentState;

    private String feeType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static SessionEntity from(Session session, Long imageId) {
        Enrollment enrollment = session.getEnrollment();
        EnrollmentCount enrollmentCount = enrollment.getEnrollmentCount();
        SessionState sessionState = enrollment.getSessionState();
        Period period = session.getPeriod();

        return new SessionEntity(session.getId(),
            session.getSessionName(),
            enrollmentCount.getRegistrationCount(),
            enrollmentCount.getMaxRegistrationCount(),
            enrollment.getTuitionFee(),
            imageId,
            sessionState.getRecruitmentState(),
            enrollment.getFeeType(),
            period.getStartDate(),
            period.getEndDate(),
            session.getCreatedAt(),
            session.getUpdatedAt());
    }

    public SessionEntity(Long id,
        String sessionName,
        int registrationCount, int maxRegistrationCount, int tuitionFee, Long imageId,
        String recruitmentState, String feeType, LocalDateTime startDate, LocalDateTime endDate,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
        this.maxRegistrationCount = maxRegistrationCount;
        this.tuitionFee = tuitionFee;
        this.imageId = imageId;
        this.recruitmentState = recruitmentState;
        this.feeType = feeType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Enrollment enrollment() {
        return new SessionEnrollment(
            EnrollmentCountFactory.get(FeeType.valueOf(feeType),
                registrationCount,
                maxRegistrationCount),
            new SessionState(SessionState.valueOfRecruitmentState(recruitmentState)),
            new Money(tuitionFee),
            FeeType.valueOf(feeType));
    }


    public Long getId() {
        return id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public int getRegistrationCount() {
        return registrationCount;
    }

    public int getMaxRegistrationCount() {
        return maxRegistrationCount;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getRecruitmentState() {
        return recruitmentState;
    }

    public String getFeeType() {
        return feeType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
