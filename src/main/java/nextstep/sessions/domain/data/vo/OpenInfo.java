package nextstep.sessions.domain.data.vo;

import java.time.LocalDateTime;

public class OpenInfo {

    private CoverImage coverImage;
    private final Duration duration;

    public OpenInfo(Duration duration) {
        this.duration = duration;
    }
    
    public LocalDateTime startDate() {
        return duration.startDate();
    }

    public LocalDateTime endDate() {
        return duration.endDate();
    }
}
