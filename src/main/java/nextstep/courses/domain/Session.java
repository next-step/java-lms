package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private Long id;
    private LocalDate startAt;
    private LocalDate endAt;
    private String imageUrl;
    private boolean isFree;
    private SessionStatus sessionStatus;
    private int maxStudents;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, LocalDate startAt, LocalDate endAt, String imageUrl,
                   boolean isFree, SessionStatus sessionStatus, int maxStudents,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.imageUrl = imageUrl;
        this.isFree = isFree;
        this.sessionStatus = sessionStatus;
        this.maxStudents = maxStudents;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(SessionStatus sessionStatus){
        this.id = 0L;
        this.startAt = LocalDate.now();
        this.endAt = LocalDate.now();
        this.imageUrl = null;
        this.isFree = true;
        this.sessionStatus = sessionStatus;
        this.maxStudents = 0;
        this.creatorId = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public boolean canRegisteringStatus() {
        return sessionStatus.getCanRegistering();
    }

    public boolean overMaxStudents(int numberOfStudents) {
        return numberOfStudents >= maxStudents;
    }
}
