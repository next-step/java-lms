package nextstep.courses.domain;

import nextstep.courses.exception.NotReadySessionException;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private Course course;

    private SessionImage image;

    private SessionCapacity capacity;

    private String name;

    private SessionType type;

    private SessionStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(Long id, Course course, SessionImage image, SessionCapacity capacity, String name, SessionType type,
                   SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.course = course;
        this.image = image;
        this.capacity = capacity;
        this.name = name;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void enroll() {
        validateSessionStatus();

        capacity.increaseNumber();
    }

    private void validateSessionStatus() {
        if (!status.isReady()) {
            throw new NotReadySessionException();
        }
    }

    public void changeStatus(SessionStatus status) {
        this.status = status;
    }

    public void changeCapacityMax(int numberMax) {
        capacity.changeNumberMax(numberMax);
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public SessionImage getImage() {
        return image;
    }

    public SessionCapacity getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
