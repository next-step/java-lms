package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Session {

    private final Long id;
    private final List<SessionImage> sessionImage;
    private RecruitStatus recruitStatus;
    private SessionProgressStatus sessionProgressStatus;
    private final Set<NsUser> students;
    private final SessionDate sessionDate;


    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate) {
        this(id, sessionImage, recruitStatus, sessionDate, new HashSet<>());
    }

    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionProgressStatus sessionProgressStatus, SessionDate sessionDate) {
        this(id, sessionImage, recruitStatus, sessionProgressStatus, sessionDate, new HashSet<>());
    }

    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionDate sessionDate, Set<NsUser> students) {
        this(id, sessionImage, recruitStatus, SessionProgressStatus.PREPARE, sessionDate, students);
    }

    public Session(Long id, List<SessionImage> sessionImage, RecruitStatus recruitStatus, SessionProgressStatus sessionProgressStatus, SessionDate sessionDate, Set<NsUser> students) {
        this.id = id;
        this.sessionImage = sessionImage;
        this.recruitStatus = recruitStatus;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionDate = sessionDate;
        this.students = students;
    }

    public final void enrollmentUser(NsUser user, Payment payment) {
        assertRecruit();
        assertNotDuplicateStudents(user);
        assertSatisfiedCondition(user, payment);

        students.add(user);
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

    public void changeRecruitStatus(RecruitStatus recruitStatus) {
        this.recruitStatus = recruitStatus;
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
