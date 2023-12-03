package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private Integer limitNumberOfStudents;
    private List<NsUser> students = new ArrayList<>();

    public Session(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, String imageURL, SessionType sessionType, Integer limitNumberOfStudents) {
        this.generation = generation;
        this.creatorId = creatorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
        this.sessionType = sessionType;
        this.limitNumberOfStudents = limitNumberOfStudents;
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, String imageURL) {
        return new Session(generation, creatorId, startDate, endDate, imageURL, SessionType.FREE, null);
    }

    public static Session create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, String imageURL, int limitNumberOfStudents) {
        return new Session(generation, creatorId, startDate, endDate, imageURL, SessionType.PAID, limitNumberOfStudents);
    }

    public void enroll(NsUser user) {
        validateEnroll();
        students.add(user);
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public void changeStatus(SessionStatus status) {
        sessionStatus = status;
    }

    public void validateEnroll() {
        if (this.sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }

        if (this.sessionType == SessionType.PAID && this.limitNumberOfStudents == students.size()) {
            throw new IllegalStateException("수강신청 정원이 가득찼습니다.");
        }
    }
}
