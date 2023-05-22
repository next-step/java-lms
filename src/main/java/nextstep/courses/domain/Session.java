package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String coverImage;

    public Session(LocalDateTime startAt, LocalDateTime endAt, String coverImage) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public String getCoverImage() {
        return coverImage;
    }
}
