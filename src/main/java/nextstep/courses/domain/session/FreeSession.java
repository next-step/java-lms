package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession() {}

    public FreeSession(Long id, PayType payType, Status status, CoverImage coverImage) {
        super(id, payType, status, coverImage);
    }

    @Override
    public void register(NsUser student) throws NotRegisterSession {
        this.sessionStudents.add(this, student);
    }
}
