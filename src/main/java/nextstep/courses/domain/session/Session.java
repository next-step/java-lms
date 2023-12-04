package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.users.domain.NsUser;

public abstract class Session {

    protected Long id;
    protected PayType payType;
    protected Status status;
    protected CoverImage coverImage;
    protected SessionStudents sessionStudents;

    public Session() {}

    public Session(Long id, PayType payType, Status status, CoverImage coverImage) {
        this.id = id;
        this.payType = payType;
        this.status = status;
        this.coverImage = coverImage;
        this.sessionStudents = new SessionStudents();
    }

    public abstract void register(NsUser nsUser);
}
