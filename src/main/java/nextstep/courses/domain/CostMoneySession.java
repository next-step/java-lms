package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class CostMoneySession extends Session {

    public CostMoneySession(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, Integer maxParticipants, SessionStatus sessionStatus, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        super(id, courseId, title, sessionImages, sessionType, sessionStatus, price, maxParticipants, startAt, endAt);
        validate(price, maxParticipants);
    }

    public static Session of(Long id, Long courseId, String title, SessionImages sessionImages, Integer maxParticipants, SessionStatus sessionStatus, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        return new CostMoneySession(id, courseId, title, sessionImages, SessionType.COST_MONEY, maxParticipants, sessionStatus, price, startAt, endAt);
    }

    @Override
    protected void validate(int price, int maxParticipants) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }

        if (maxParticipants < 0) {
            throw new IllegalArgumentException("정원은 음수일 수 없습니다.");
        }
    }

    @Override
    public void register(NsUser nsUser, Payment payment) {
        validateRegister(payment);
        super.students.add(nsUser);
    }
}
