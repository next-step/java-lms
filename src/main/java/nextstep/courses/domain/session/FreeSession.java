package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

import static nextstep.courses.domain.session.Status.isNotRecruiting;

public class FreeSession extends Session {

    public FreeSession() {}

    public FreeSession(Long id, PayType payType, Status status, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        super(id, payType, status, coverImage, startDate, endDate);
    }

    @Override
    public void enroll(SessionStudent sessionStudent, Payment payment) {
        validateStatus();
        this.sessionStudents.add(sessionStudent);
    }

    private void validateStatus() {
        if (isNotRecruiting(status)) {
            throw new IllegalArgumentException(String.format("해당 강의의 현재 %s입니다.", status.description()));
        }
    }
}
