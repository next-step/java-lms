package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;

abstract class Session {

    private Long id;
    private SessionPeriod sessionPeriod;
    private CoverImage coverImage;
    private SessionType sessionType;
    private SessionFee sessionFee;
    private List<NsUser> students;
    private SessionStatus sessionStatus;
    private int maxCapacity;

    protected Session(Long id, SessionPeriod sessionPeriod, CoverImage coverImage, SessionType sessionType, SessionFee sessionFee, List<NsUser> students, SessionStatus sessionStatus, int maximumCapacity) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionFee = sessionFee;
        this.students = students;
        this.sessionStatus = sessionStatus;
        this.maxCapacity = maximumCapacity;
    }

    public abstract void enroll(NsUser user, Payment payment);

    public void start() {
        sessionStatus = SessionStatus.RECRUIT;
    }

    public int countOfStudents() {
        return students.size();
    }

    public SessionType sessionType() {
        return this.sessionType;
    }

    public SessionStatus sessionStatus() {
        return this.sessionStatus;
    }

    protected void addStudent(NsUser user) {
        students.add(user);
    }

    protected int maxCapacity() {
        return this.maxCapacity;
    }

    protected boolean isRecruting() {
        return SessionStatus.RECRUIT == this.sessionStatus();
    }

    protected boolean isFull() {
        return this.countOfStudents() == this.maxCapacity();
    }

    protected boolean hasPaid(Long fee) {
        return this.sessionFee.hasPaid(fee);
    }
}
