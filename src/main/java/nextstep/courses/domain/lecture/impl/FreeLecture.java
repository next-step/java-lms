package nextstep.courses.domain.lecture.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureName;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.courses.domain.lecture.RegistrationCount;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class FreeLecture implements Lecture {

    private final RegistrationCount registrationCount;

    private final LectureName lectureName;

    private final Money tuitionFee;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final LectureStatus lectureStatus;

    public FreeLecture(LectureName lectureName, LocalDateTime startDate, LocalDateTime endDate,
        LectureStatus lectureStatus) {
        this(new RegistrationCount(0), lectureName, new Money(0), startDate, endDate,
            lectureStatus);
    }

    public FreeLecture(RegistrationCount registrationCount, LectureName lectureName,
        Money tuitionFee,
        LocalDateTime startDate, LocalDateTime endDate, LectureStatus lectureStatus) {
        this.registrationCount = registrationCount;
        this.lectureName = lectureName;
        this.tuitionFee = tuitionFee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureStatus = lectureStatus;
    }

    @Override
    public void addRegistrationCount() {
        registrationCount.addValue();
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
