package nextstep.courses.entity;

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

    public SessionEntity(Long courseId, String state, Long imageId, LocalDateTime startDate, LocalDateTime endDate, int numberOfStudent, int maxNumberOfStudent, Long fee) {
        this(null, courseId, state, imageId, startDate, endDate, numberOfStudent, maxNumberOfStudent, fee);
    }

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

    public Long getId() {
        return id;
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
