package nextstep.session.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {

    private Long id;

    private int generation;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDate startDate;

    private LocalDate endDate;

    private String imageURL;

    private SessionStatus sessionStatus;

    private SessionType sessionType;

    public Session(int generation, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDate startDate, LocalDate endDate, String imageURL, SessionStatus sessionStatus, SessionType sessionType) {
        this.generation = generation;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }
}
