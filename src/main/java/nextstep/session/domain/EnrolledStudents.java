package nextstep.session.domain;

import nextstep.payments.domain.PaymentPolicy;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class EnrolledStudents {

    private Long id;
    private Long sessionId;

    List<NsUser> students = new ArrayList<>();

    public void add(PaymentPolicy policy, NsUser user) {
        if ( ! policy.canEnroll(count()) ) {
            throw new IllegalStateException("더이상 학생을 추가할 수 없습니다. 최대 학생수=" + policy.enrollmentLimit() + ", 현재 학생수=" + count());
        }

        students.add(user);
    }

    public int count() {
        return students.size();
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
