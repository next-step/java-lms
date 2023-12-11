package nextstep.courses.service;

import nextstep.courses.dto.EnrolmentInfo;
import nextstep.courses.domain.session.PayType;

public interface SessionService {

    boolean supports(PayType payType);
    void enroll(EnrolmentInfo enrolmentInfo);
}
