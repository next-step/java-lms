package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;

public abstract class Session {

    public static final String ENROLLMENT_ALREADY_DONE = "이미 수강 신청을 완료하신 강의입니다.";

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

    protected Session(Long id, SessionPeriod sessionPeriod, CoverImage coverImage, SessionType sessionType, SessionFee sessionFee, List<NsUser> students, SessionStatus sessionStatus) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionFee = sessionFee;
        this.students = students;
        this.sessionStatus = sessionStatus;
    }

    public abstract void enroll(NsUser user, Payment payment);

    public final void validateRecruting() {
        if (!isRecruting()) {
            throw new IllegalStateException("강의 모집중이 아닙니다.");
        }
    }

    public final void validateEnrolled(NsUser nsUser, Session session) {
        if (hasAlreadyEnrolled(nsUser, this)) {
            throw new IllegalArgumentException(ENROLLMENT_ALREADY_DONE);
        }
    }

    private boolean hasAlreadyEnrolled(NsUser nsUser, Session session) {
        return  this.students.contains(nsUser);
    }

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
