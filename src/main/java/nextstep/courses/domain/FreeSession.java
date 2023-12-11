package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeSession extends Session {

    public FreeSession(Image image, Period period) {
        super(image, period, SessionType.FREE);
    }

    @Override
    public void enroll(NsUser student, Payment payment) {
        if (SessionStatus.RECRUITING != this.getStatus()) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        this.getStudents().add(student);
    }
}
