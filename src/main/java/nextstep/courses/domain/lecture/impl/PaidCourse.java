package nextstep.courses.domain.lecture.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.courses.domain.lecture.MaxRegistrationCount;
import nextstep.courses.domain.lecture.RegistrationCount;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class PaidCourse implements Lecture {

    private final RegistrationCount registrationCount;

    private final MaxRegistrationCount maxRegistrationCount;

    private final Money tuitionFee;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private LectureStatus lectureStatus;

    public PaidCourse(RegistrationCount registrationCount,
        MaxRegistrationCount maxRegistrationCount,
        Money tuitionFee, LocalDateTime startDate, LocalDateTime endDate,
        LectureStatus lectureStatus) {
        this.registrationCount = registrationCount;
        this.maxRegistrationCount = maxRegistrationCount;
        this.tuitionFee = tuitionFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureStatus = lectureStatus;
    }

    public boolean isEnrollCourse() {
        return true;
    }

    @Override
    public boolean isRegistrationAvailable() {
        return isRecruitmentOpen(lectureStatus)
            && maxRegistrationCount.isMaxRegistrationCountOver(this.registrationCount);
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment){
        return payment.isSamePaymentAmount(tuitionFee);
    }
}
