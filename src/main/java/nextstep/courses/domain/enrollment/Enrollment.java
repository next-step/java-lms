package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.CannotEnrollException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    public void enroll(Session session, NsUser user, Payment payment) throws CannotEnrollException {
        if (!session.isOpen()) {
            throw new CannotEnrollException("강의 수강신청은 강의 상태가 모집중일 때만 가능하다");
        }
        if (session.isPaid() && session.isMaxStudents()) {
            throw new CannotEnrollException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다");
        }
        if (session.duplicateUser(user)) {
            throw new CannotEnrollException("강의는 중복 수강신청할 수 없다");
        }
        if (session.isPaid() && !payment.isValid(session.getPrice())) {
            throw new CannotEnrollException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다");
        }
        session.addStudent(user);
    }
}
