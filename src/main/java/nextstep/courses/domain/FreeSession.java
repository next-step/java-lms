package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession extends Session {

    private static final Integer FREE_PRICE = 0;
    private static final Integer NO_LIMITATION = 0;

    private final Integer maxParticipants;
    private final Integer price;

    public FreeSession(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus sessionStatus, LocalDateTime startAt, LocalDateTime endAt) {
        super(id, courseId, title, sessionImages, sessionType, sessionStatus, startAt, endAt);
        validate(price, maxParticipants);
        this.maxParticipants = maxParticipants;
        this.price = price;
    }

    public static FreeSession of(Long id, Long courseId, String title, SessionImages sessionImages, SessionStatus sessionStatus, LocalDateTime startAt, LocalDateTime endAt) {
        return new FreeSession(id, courseId, title, sessionImages, SessionType.FOR_FREE, NO_LIMITATION, FREE_PRICE, sessionStatus, startAt, endAt);
    }

    private void validate(int price, int maxParticipants) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }

        if (maxParticipants < 0) {
            throw new IllegalArgumentException("정원은 음수일 수 없습니다.");
        }
    }

    public void registerFreeSession(NsUser nsUser) {
        super.validateSessionIsRegistering();
        super.students.add(nsUser);
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public int getPrice() {
        return this.price;
    }
}
