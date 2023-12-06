package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Session {
    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    private Long id;

    private int generation;

    private Long creatorId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDate startDate;

    private LocalDate endDate;

    private SessionImage sessionImage;

    protected SessionStatus sessionStatus = DEFAULT_SESSION_STATUS;
    protected SessionStudents students = new SessionStudents();

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
    }

    public void enroll(NsUser user) {
    }

    public List<NsUser> getStudents() {
        return students.getStudents();
    }

    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }
}
