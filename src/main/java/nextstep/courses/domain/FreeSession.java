package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession extends Session {

    private static final Integer FREE_PRICE = 0;
    private static final Integer NO_LIMITATION = 0;

    private FreeSession(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus sessionStatus, LocalDateTime startAt, LocalDateTime endAt) {
        super(id, courseId, title, sessionImages, sessionType, sessionStatus, price, maxParticipants, startAt, endAt);
        validate(price, maxParticipants);
    }

    private FreeSession(Long id, Long courseId, String title, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus status, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, courseId, title, sessionType, maxParticipants, price, status, startAt, endAt, createdAt, updatedAt);
        validate(price, maxParticipants);
    }

    public static Session of(Long id, Long courseId, String title, SessionImages sessionImages, SessionStatus sessionStatus, LocalDateTime startAt, LocalDateTime endAt) {
        return new FreeSession(id, courseId, title, sessionImages, SessionType.FOR_FREE, NO_LIMITATION, FREE_PRICE, sessionStatus, startAt, endAt);
    }

    public static Session of(Long id, Long courseId, String title, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus status, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new FreeSession(id, courseId, title, sessionType, maxParticipants, price, status, startAt, endAt, createdAt, updatedAt);
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
