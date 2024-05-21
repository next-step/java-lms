package nextstep.courses.entity;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.*;

import java.time.LocalDateTime;

public class SessionEntity {
    private Long id;

    private Long courseId;

    private String state;

    private Long imageId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int numberOfStudent;

    private int maxNumberOfStudent = 0;

    private Long fee = 0L;

    public SessionEntity(Long id, Long courseId, String state, Long imageId, LocalDateTime startDate, LocalDateTime endDate, int numberOfStudent, int maxNumberOfStudent, Long fee) {
        this.id = id;
        this.courseId = courseId;
        this.state = state;
        this.imageId = imageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfStudent = numberOfStudent;
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.fee = fee;
    }

    public Session convert() {
        if(fee > 0) {
            return new PaidSession(id,
                    new Course(courseId),
                    SessionState.valueOf(state),
                    new SessionImage(imageId),
                    startDate,
                    endDate,
                    numberOfStudent,
                    maxNumberOfStudent,
                    fee);
        }

        return new FreeSession(id,
                new Course(courseId),
                SessionState.valueOf(state),
                new SessionImage(imageId),
                startDate,
                endDate,
                numberOfStudent);
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getState() {
        return state;
    }

    public Long getImageId() {
        return imageId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public int getMaxNumberOfStudent() {
        return maxNumberOfStudent;
    }

    public Long getFee() {
        return fee;
    }
}
