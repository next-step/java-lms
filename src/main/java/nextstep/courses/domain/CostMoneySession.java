package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionProgressType;
import nextstep.courses.enumeration.SessionRecruitStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.exception.ExceedStudentsCountException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class CostMoneySession extends Session {

    public CostMoneySession(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, Integer maxParticipants, SessionRecruitStatus sessionRecruitStatus, SessionProgressType sessionProgressType, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        super(id, courseId, title, sessionImages, sessionType, sessionRecruitStatus, sessionProgressType, price, maxParticipants, startAt, endAt);
        validate(price, maxParticipants);
    }

    public static Session of(Long id, Long courseId, String title, SessionImages sessionImages, Integer maxParticipants, SessionRecruitStatus sessionRecruitStatus, SessionProgressType sessionProgressType, Integer price, LocalDateTime startAt, LocalDateTime endAt) {
        return new CostMoneySession(id, courseId, title, sessionImages, SessionType.COST_MONEY, maxParticipants, sessionRecruitStatus, sessionProgressType, price, startAt, endAt);
    }

    @Override
    protected void validate(int price, int maxParticipants) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }

        if (maxParticipants < 0) {
            throw new IllegalArgumentException("정원은 음수일 수 없습니다.");
        }

        if (super.getSessionType().equals(SessionType.COST_MONEY) && super.sessionStudents.isMaxParticipants(maxParticipants)) {
            throw new ExceedStudentsCountException("정원이 초과되어 더 이상 신청할 수 없습니다.");
        }
    }

    @Override
    public void enroll(SessionStudent sessionStudent, Payment payment) {
        super.sessionStudents.add(sessionStudent);
    }
}
