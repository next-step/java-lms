package nextstep.courses.domain.lecture.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureName;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.courses.domain.lecture.RegistrationCount;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class FreeLecture implements Lecture {

    private final LectureName lectureName;

    private final RegistrationCount registrationCount;

    private final Money tuitionFee;

    private final Image image;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final LectureStatus lectureStatus;

    public FreeLecture(LectureName lectureName, LocalDateTime startDate, LocalDateTime endDate, Image image,
        LectureStatus lectureStatus) {
        this(lectureName, new RegistrationCount(0), new Money(0), image, startDate, endDate,
            lectureStatus);
    }

    public FreeLecture(LectureName lectureName, RegistrationCount registrationCount,
        Money tuitionFee,
        Image image, LocalDateTime startDate, LocalDateTime endDate, LectureStatus lectureStatus) {
        this.lectureName = lectureName;
        this.registrationCount = registrationCount;
        this.tuitionFee = tuitionFee;
        this.image = image;
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
