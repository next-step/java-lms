package nextstep.session.domain;

import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.payments.domain.Payment;
import nextstep.session.dto.SessionVO;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public interface Session {

    void toNextSessionStatus();

    void toPreviousSessionStatus();

    boolean isEnrollAvailable(LocalDateTime applyDate);

    boolean apply(Student student, Payment payment, LocalDateTime applyDate);

    DeleteHistoryTargets delete(NsUser requestUser);

    SessionVO toVO();

    Cover getCover();

    Students getStudents();
}
