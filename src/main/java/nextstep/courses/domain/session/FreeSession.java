package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.exception.NotRegisterSession;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession() {}

    public FreeSession(Long id, PayType payType, Status status, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        super(id, payType, status, coverImage, startDate, endDate);
    }

    @Override
    public void enroll(SessionStudent sessionStudent) throws NotRegisterSession {
        this.sessionStudents.add(sessionStudent);
    }
}
