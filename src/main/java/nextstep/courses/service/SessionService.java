package nextstep.courses.service;

import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.dto.EnrolmentInfo;
import nextstep.courses.domain.session.enums.PayType;
import nextstep.courses.dto.SelectInfo;

public interface SessionService {

    boolean supports(PayType payType);
    void enroll(EnrolmentInfo enrolmentInfo);
    SessionStudent selection(SelectInfo selectInfo);
}
