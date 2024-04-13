package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public abstract class Session {

    private Long id;
    private final SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private final Set<NsUser> students = new HashSet<>();

    public Session(SessionImage sessionImage, SessionStatus sessionStatus) {
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
    }

    public final Payment enrollmentUser(NsUser user) {

        assertRecruit(user);

        students.add(user);

        return payResult(user);
    }

    public Set<NsUser> getStudents() {
        return new HashSet<>(students);
    }

    public Long getId() {
        return id;
    }

    public SessionImage getSessionImage() {
        return sessionImage;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    abstract protected void assertRecruit(NsUser user);

    abstract protected Payment payResult(NsUser user);
}
