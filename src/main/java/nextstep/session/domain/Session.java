package nextstep.session.domain;

import nextstep.payments.domain.Payment;
import nextstep.session.dto.SessionDto;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public interface Session {

    void changeStartDate(LocalDateTime startDate);

    void changeEndDate(LocalDateTime endDate);

    void changeCover(Cover cover);

    void toNextSessionStatus();

    void toPreviousSessionStatus();

    void editSessionName(String sessionName);

    boolean isEnrollAvailable(LocalDateTime applyDate);

    boolean apply(Student student, Payment payment, LocalDateTime applyDate);

    SessionDto toDto();
}
