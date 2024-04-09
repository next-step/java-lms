package nextstep.courses.domain.lecture.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class FreeLecture implements Lecture {

    private final Money tuitionFee;
    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final LectureStatus lectureStatus;

    public FreeLecture(LocalDateTime startDate, LocalDateTime endDate,
        LectureStatus lectureStatus) {
        this(new Money(0), startDate, endDate, lectureStatus);
    }

    public FreeLecture(Money tuitionFee, LocalDateTime startDate, LocalDateTime endDate,
        LectureStatus lectureStatus) {
        this.tuitionFee = tuitionFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureStatus = lectureStatus;
    }

    @Override
    public boolean isRegistrationAvailable() {
        return isRecruitmentOpen(lectureStatus);
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment) {
        return payment.isSamePaymentAmount(tuitionFee);
    }
}
