package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final String title;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final CoverImage coverImage;
    private final PriceType priceType;
    private final Price price;
    private final List<NsUser> attendees = new ArrayList<>();
    private State state = State.OPEN;
    private Integer MaxAttendees = null;

    public Session(String title, LocalDateTime startAt, LocalDateTime endAt, CoverImage coverImage, PriceType priceType, Price price, Integer maxAttendees) {
        validatePaidPolicy(priceType, price, maxAttendees);
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.priceType = priceType;
        this.price = price;
        this.MaxAttendees = maxAttendees;
    }

    public Session(String title, LocalDateTime startAt, LocalDateTime endAt, CoverImage coverImage, PriceType priceType) {
        validateFreePolicy(priceType);
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.priceType = priceType;
        this.price = new Price(0);
    }

    public void close() {
        this.state = State.CLOSED;
    }

    public void open() {
        this.state = State.OPEN;
    }

    public State getState() {
        if (startAt.isAfter(LocalDateTime.now())) {
            return State.READY;
        } else if (endAt.isAfter(LocalDateTime.now())) {
            return state;
        } else {
            return State.CLOSED;
        }
    }

    public void apply(NsUser user) {
        if (getState() != State.OPEN) {
            throw new IllegalStateException("강의가 모집중이 아닙니다.");
        }
        attendees.add(user);
        if (priceType == PriceType.PAID && attendees.size() == MaxAttendees) {
            close();
        }
    }

    public String getTitle() {
        return title;
    }

    public List<NsUser> getAttendees() {
        return List.copyOf(attendees);
    }

    private void validatePaidPolicy(PriceType priceType, Price price, Integer maxAttendees) {
        if (priceType != PriceType.PAID) {
            throw new IllegalArgumentException("무료 강의는 가격과 최대 인원이 필요하지 않습니다.");
        }
        if (price == null || maxAttendees == null) {
            throw new IllegalArgumentException("유료 강의는 가격과 최대 인원이 필요합니다.");
        }
        if (maxAttendees <= 0) {
            throw new IllegalArgumentException("최대 수강 인원은 0보다 커야 합니다.");
        }
    }

    private void validateFreePolicy(PriceType priceType) {
        if (priceType != PriceType.FREE) {
            throw new IllegalArgumentException("유료 강의는 가격과 최대 인원이 필요합니다.");
        }
    }

}
