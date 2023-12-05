package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface Session {
    
    Attendee enroll(Payment payment, NsUser nsUser);
}
