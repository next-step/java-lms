package nextstep.courses.service;

import nextstep.courses.domain.session.student.Student;
import nextstep.courses.dto.EnrolmentInfo;
import nextstep.courses.domain.session.PayType;
import nextstep.courses.dto.SelectInfo;

public interface SessionService {

    boolean supports(PayType payType);
    void enroll(EnrolmentInfo enrolmentInfo);
    Student selection(SelectInfo selectInfo);
}
