package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.exception.NotMatchAmountException;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public abstract class Session {

    protected Long id;
    protected PayType payType;
    protected Status status;
    protected CoverImage coverImage;
    protected SessionStudents sessionStudents;

    protected Session() {}

    protected Session(Long id, PayType payType, Status status, CoverImage coverImage) {
        this.id = id;
        this.payType = payType;
        this.status = status;
        this.coverImage = coverImage;
        this.sessionStudents = new SessionStudents();
    }

    public void isEqual(Long amount) throws NotMatchAmountException {
        return;
    }

    public abstract void enroll(NsUser nsUser) throws NotRegisterSession;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && payType == session.payType && status == session.status && Objects.equals(coverImage, session.coverImage) && Objects.equals(sessionStudents, session.sessionStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payType, status, coverImage, sessionStudents);
    }
}
