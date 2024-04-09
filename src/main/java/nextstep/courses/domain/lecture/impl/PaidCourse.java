package nextstep.courses.domain.lecture.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureStatus;
import nextstep.courses.domain.lecture.MaxRegistrationCount;
import nextstep.courses.domain.lecture.RegistrationCount;

public class PaidCourse implements Lecture {

    private final RegistrationCount registrationCount;

    private final MaxRegistrationCount maxRegistrationCount;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private LectureStatus lectureStatus;

    public PaidCourse(RegistrationCount registrationCount,
        MaxRegistrationCount maxRegistrationCount,
        LocalDateTime startDate, LocalDateTime endDate, LectureStatus lectureStatus) {
        this.registrationCount = registrationCount;
        this.maxRegistrationCount = maxRegistrationCount;
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
}
