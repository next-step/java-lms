package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.*;

public abstract class Session {

    private final Long id;
    private final List<SessionImage> sessionImage;
    private RecruitStatus recruitStatus;
    private SessionProgressStatus sessionProgressStatus;
    private final Set<NsUser> students;
    private final Set<NsUser> approveStudents;
    private final SessionDate sessionDate;


    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate) {
        this(id, sessionImage, recruitStatus, sessionDate, new HashSet<>());
    }

    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionProgressStatus sessionProgressStatus, SessionDate sessionDate) {
        this(id, sessionImage, recruitStatus, sessionProgressStatus, sessionDate, new HashSet<>(), new HashSet<>());
    }

    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate, Set<NsUser> students) {
        this(id, sessionImage, recruitStatus, SessionProgressStatus.PREPARE, sessionDate, students, new HashSet<>());
    }

    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionProgressStatus sessionProgressStatus, SessionDate sessionDate, Set<NsUser> students, Set<NsUser> approveStudents) {
        this.id = id;
        this.sessionImage = sessionImage;
        this.recruitStatus = recruitStatus;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionDate = sessionDate;
        this.students = students;
        this.approveStudents = approveStudents;

    }

    public final void enrollmentUser(NsUser user, Payment payment) {
        assertRecruit();
        assertNotDuplicateStudents(user);
        assertSatisfiedCondition(user, payment);

        students.add(user);
    }

    public final void removeNotApproveUser() {
        students.removeIf(student -> !approveStudents.contains(student));
    }

    public final void addApproveStudent(NsUser nsUser) {
        approveStudents.add(nsUser);
    }

    private void assertNotDuplicateStudents(NsUser user) {
        if (students.contains(user)) {
            throw new NotRecruitException();
        }
    }

    private void assertRecruit() {
        if (!recruitStatus.isRecruit()) {
            throw new NotRecruitException();
        }
    }

    public Set<NsUser> getStudents() {
        return new HashSet<>(students);
    }

    public Set<NsUser> getApproveStudents() {
        return new HashSet<>(approveStudents);
    }

    public Long getId() {
        return id;
    }

    public List<SessionImage> getSessionImage() {
        return sessionImage;
    }

    public RecruitStatus getRecruitStatus() {
        return recruitStatus;
    }

    public SessionProgressStatus getSessionProgressStatus() {
        return sessionProgressStatus;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public void changeProgressStatus(SessionProgressStatus sessionProgressStatus) {
        this.sessionProgressStatus = sessionProgressStatus;
    }


    abstract protected void assertSatisfiedCondition(NsUser user, Payment payment);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, this.getClass());
    }
}
