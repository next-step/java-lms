package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private final long id;
    private String title;
    private int maxNumberOfStudent;
    private CoverImage coverImage;
    private LocalDateTime startDt = null;
    private LocalDateTime endDt = null;
    private boolean free;
    private SessionStatus sessionStatus = SessionStatus.PREPARING;
    private Students students = new Students();

    public Session(long id, String title, int maxNumberOfStudent, boolean free) {
        this(id, title, maxNumberOfStudent, free, null);
    }

    public Session(long id, String title, int maxNumberOfStudent, boolean free, CoverImage coverImage) {
        this.id = id;
        this.title = title;
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.free = free;
        this.coverImage = coverImage;
    }

    public void enrolment(NsUser user) throws IllegalAccessException {
        checkedProceeding();

        checkedMaxNumberOfStudent();

        students.addStudent(user);
    }

    private void checkedMaxNumberOfStudent() throws IllegalAccessException {
        if (!students.isPossibleAdd(maxNumberOfStudent)) {
            throw new IllegalAccessException("수강 인원이 가득 찼습니다.");
        }
    }

    private void checkedProceeding() throws IllegalAccessException {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalAccessException("모집 중이 아닙니다.");
        }
    }

    public SessionStatus startRecruiting() {
        return changeSessionStatus(SessionStatus.PREPARING, SessionStatus.RECRUITING);
    }

    public SessionStatus startSession() {
        SessionStatus sessionStatus = changeSessionStatus(SessionStatus.RECRUITING, SessionStatus.PROCEEDING);
        this.startDt = LocalDateTime.now();
        return sessionStatus;
    }

    public SessionStatus endSession() {
        SessionStatus sessionStatus = changeSessionStatus(SessionStatus.PROCEEDING, SessionStatus.END);
        this.endDt = LocalDateTime.now();
        return sessionStatus;
    }

    private SessionStatus changeSessionStatus(SessionStatus compareStatus, SessionStatus changeStatus) {
        if (this.sessionStatus != compareStatus) {
            throw new NotChangeStatusException();
        }

        return this.sessionStatus = changeStatus;
    }

    public void addCoverImage(CoverImage coverImage){
        this.coverImage = coverImage;
    }
}
