package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.courses.exception.SessionFullException;

public class PaidEnrollmentStrategy implements EnrollmentStrategy {

    @Override
    public void enroll(long payment,
                       Amount amount,
                       int capacity,
                       Student student,
                       Students students) {
        amount.validateAmount(payment);

        if (students.isFull(capacity)) {
            throw new SessionFullException("수강 신청 인원이 마감 되었습니다.");
        }

        students.enrol(student);
    }

}
