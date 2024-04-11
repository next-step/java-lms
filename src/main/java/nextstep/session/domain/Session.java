package nextstep.session.domain;

import nextstep.common.domain.DeleteHistory;
import nextstep.payments.domain.Payment;
import nextstep.session.dto.SessionVO;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public interface Session {

    void toNextSessionStatus();

    void toPreviousSessionStatus();

    boolean isEnrollAvailable(LocalDateTime applyDate);

    boolean apply(Student student, Payment payment, LocalDateTime applyDate);

    DeleteHistory delete(NsUser requestUser);

    SessionVO toVO();
}
