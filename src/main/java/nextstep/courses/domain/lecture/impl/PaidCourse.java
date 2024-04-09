package nextstep.courses.domain.lecture.impl;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.LectureStatus;

public class PaidCourse {

    private final int maxEnrollment;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private LectureStatus lectureStatus;

    public PaidCourse(int maxEnrollment, LocalDateTime startDate, LocalDateTime endDate,
        LectureStatus lectureStatus) {
        this.maxEnrollment = maxEnrollment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureStatus = lectureStatus;
    }

    public boolean isEnrollCourse(){
        return true;
    }
}
