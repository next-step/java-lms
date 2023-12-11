package nextstep.courses.domain.session.enrollment2;

import nextstep.courses.type.ProgressState;
import nextstep.courses.type.RecruitState;
import nextstep.courses.type.SessionType;

public class EnrollmentInfo {

    private ProgressState progressState;
    private RecruitState recruitState;

    private final Long amount;
    private final Long enrollmentMax;

    private final Enroll enroll;

    public EnrollmentInfo(SessionType sessionType, ProgressState progressState, RecruitState recruitState, Long amount, Long enrollmentMax) {
        this.progressState = progressState;
        this.recruitState = recruitState;
        this.amount = amount;
        this.enrollmentMax = enrollmentMax;
        this.enroll = Enroll.from(sessionType);
    }

    public static EnrollmentInfo of(SessionType sessionType, ProgressState progressState, RecruitState recruitState, Long amount, Long enrollmentMax) {
        return new EnrollmentInfo(sessionType, progressState, recruitState, amount, enrollmentMax);
    }


    public ProgressState progressState() {
        return progressState;
    }

    public RecruitState recruitState() {
        return recruitState;
    }

    public Long amount() {
        return amount;
    }

    public Long enrollmentMax() {
        return enrollmentMax;
    }

    public Enroll enroll() {
        return enroll;
    }
}
