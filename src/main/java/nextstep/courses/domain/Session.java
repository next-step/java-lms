package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Session {

    private Long id;
    private final SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private final Set<NsUser> students = new HashSet<>();

    private final SessionDate sessionDate;

    public Session(SessionImage sessionImage, SessionStatus sessionStatus, SessionDate sessionDate) {
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
        this.sessionDate = sessionDate;
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

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    abstract protected void assertRecruit(NsUser user);

    abstract protected Payment payResult(NsUser user);
}
