package nextstep.session.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    private Long id;

    private int generation;

    private Long creatorId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDate startDate;

    private LocalDate endDate;

    private String imageURL;

    private SessionStatus sessionStatus = DEFAULT_SESSION_STATUS;

    private SessionType sessionType;

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, String imageURL, SessionType sessionType) {
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
        this.sessionType = sessionType;
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, String imageURL, SessionType sessionType) {
        return new Session(generation, creatorId, startDate, endDate, imageURL, sessionType);
    }
}
