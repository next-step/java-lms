package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.payments.domain.Payment;

import java.util.List;

abstract public class Session {
    protected final Long id;
    protected final Course course;
    protected final SessionInfos sessionInfos;
    protected SessionType sessionType;
    protected int numberOfStudents;
    protected List<CoverImageInfo> coverImageInfos;

    protected Session(Long id, Course course, SessionInfos sessionInfos, SessionType sessionType, int numberOfStudents, List<CoverImageInfo> coverImageInfos) {
        this(id, course, sessionInfos, sessionType, numberOfStudents);
        this.coverImageInfos = coverImageInfos;
    }

    protected Session(Long id, Course course, SessionInfos sessionInfos, SessionType sessionType, int numberOfStudents) {
        this.id = id;
        this.course = course;
        this.sessionInfos = sessionInfos;
        this.sessionType = sessionType;
        this.numberOfStudents = numberOfStudents;
    }

    abstract public void enroll(Payment payment);

    public void startRecruit() {
        sessionInfos.startRecruit();
    }

    public boolean hasNumberOfStudents(int targetCount) {
        return numberOfStudents == targetCount;
    }

    public Long getId() {return id;}

    public Course getCourse() {return course;}

    public SessionDate getSessionDate() {
        return sessionInfos.getSessionDate();
    }

    public SessionStatus getSessionStatus() {
        return sessionInfos.getSessionStatus();
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void addCoverImageInfo(CoverImageInfo coverImageInfo) {
        this.coverImageInfos.add(coverImageInfo);
    }

    public SessionType getType() {
        return sessionType;
    }

    public boolean isRecruiting() {
        return sessionInfos.isStatusNotRecruiting();
    }
}
