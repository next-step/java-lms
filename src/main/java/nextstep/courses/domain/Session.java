package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.payments.domain.EnrollmentPolicy;

import java.time.LocalDate;

public class Session {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final CoverImage coverImage;
    private final SessionStatus status;
    private final EnrollmentPolicy enrollmentPolicy;

    private int currentEnrolledCount = 0;

    public Session(LocalDate startDate,
                   LocalDate endDate,
                   CoverImage coverImage,
                   SessionStatus status,
                   EnrollmentPolicy enrollmentPolicy) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.status = status;
        this.enrollmentPolicy = enrollmentPolicy;
    }

    public void enroll(int paidAmount) {
        if (!this.status.canEnroll()) {
            throw new CannotEnrollSessionException("모집 중이 아닙니다.");
        }

        if (!enrollmentPolicy.canEnroll(currentEnrolledCount, paidAmount)) {
            throw new CannotEnrollSessionException("수강 조건이 맞지 않습니다.");
        }

        currentEnrolledCount++;
    }

}
