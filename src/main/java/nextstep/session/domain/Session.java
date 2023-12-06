package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Session {
    protected static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.PREPARING;

    protected Long id;

    protected int generation;

    protected Long creatorId;

    protected LocalDateTime createdAt = LocalDateTime.now();

    protected LocalDateTime updatedAt = LocalDateTime.now();

    protected LocalDate startDate;

    protected LocalDate endDate;

    protected SessionImage sessionImage;

    protected SessionStatus sessionStatus = DEFAULT_SESSION_STATUS;

    protected SessionType sessionType;

    protected Integer limitNumberOfStudents;
    protected SessionStudents students = new SessionStudents();
    protected Long price;

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents) {
        validateSessionType(sessionType, limitNumberOfStudents);
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
        this.sessionType = sessionType;
        this.limitNumberOfStudents = limitNumberOfStudents;
    }

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents, Long price) {
        validateSessionType(sessionType, limitNumberOfStudents);
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
        this.sessionType = sessionType;
        this.limitNumberOfStudents = limitNumberOfStudents;
        this.price = price;
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        return new Session(generation, creatorId, startDate, endDate, sessionImage, SessionType.FREE, null);
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents, Long price) {
        return new Session(generation, creatorId, startDate, endDate, sessionImage, sessionType, limitNumberOfStudents, price);
    }

    private static void validateSessionType(SessionType sessionType, Integer limitNumberOfStudents) {
        if (sessionType == SessionType.FREE && limitNumberOfStudents != null) {
            throw new IllegalArgumentException("무료강의는 최대 수강 인원 제한이 없습니다.");
        }
        if (sessionType == SessionType.PAID && limitNumberOfStudents == null) {
            throw new IllegalArgumentException("유료강의는 최대 수강 인원 제한이 있습니다.");
        }
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
