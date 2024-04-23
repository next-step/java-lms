package nextstep.courses.domain.Session;

import java.time.LocalDate;

public class SessionEntity {
    private Long id;
    private String name;
    private String coverImage;
    private int fee;
    private SessionStatus sessionStatus;
    private int maxStudents;
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionEntity() {

    }

    public SessionEntity(Long id, String name, String coverImage, int fee, SessionStatus sessionStatus, int maxStudents, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.coverImage = coverImage;
        this.fee = fee;
        this.sessionStatus = sessionStatus;
        this.maxStudents = maxStudents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SessionDuration sessionDuration() {
        return new SessionDuration(startDate, endDate);
    }
}
