package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private Long courseId;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private CoverImage coverImage;
    private PriceType priceType;
    private Integer price;
    private Integer maxAttendees;
    private State state = State.OPEN;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final List<NsUser> attendees = new ArrayList<>();

    public Session(Long id, Long courseId, String title, LocalDateTime startAt, LocalDateTime endAt, CoverImage coverImage, PriceType priceType, Integer price, Integer maxAttendees, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.priceType = priceType;
        this.price = price;
        this.maxAttendees = maxAttendees;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validate();
    }

    public Session(Long courseId, String title, LocalDateTime startAt, LocalDateTime endAt, CoverImage coverImage, PriceType priceType, Integer price, Integer maxAttendees) {
        this(0L, courseId, title, startAt, endAt, coverImage, priceType, price, maxAttendees, LocalDateTime.now(), LocalDateTime.now());
    }

    public void close() {
        this.state = State.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }

    public void open() {
        this.state = State.OPEN;
        this.updatedAt = LocalDateTime.now();
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
        if (priceType == PriceType.PAID && attendees.size() == maxAttendees) {
            close();
        }
    }

    public Long getId() {
        return id;
    }

    public String getCourseId() {
        return String.valueOf(courseId);
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getmaxAttendees() {
        return maxAttendees;
    }

    public List<NsUser> getAttendees() {
        return List.copyOf(attendees);
    }

    private void validate() {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }
        if (priceType == PriceType.FREE && (price != 0 || maxAttendees != null)) {
            throw new IllegalArgumentException("무료 강의는 가격과 최대 인원이 필요하지 않습니다.");
        }
        if (priceType == PriceType.PAID && maxAttendees == null) {
            throw new IllegalArgumentException("유료 강의는 최대 인원이 필요합니다.");
        }
        if (priceType == PriceType.PAID && price == 0) {
            throw new IllegalArgumentException("유료 강의는 가격이 0원보다 커야 합니다.");
        }
        if (maxAttendees <= 0) {
            throw new IllegalArgumentException("최대 수강 인원은 0보다 커야 합니다.");
        }
    }
}
