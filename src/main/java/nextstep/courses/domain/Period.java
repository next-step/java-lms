package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Period {
    private final LocalDateTime beginDt;
    private final LocalDateTime endDt;

    public Period(LocalDateTime beginDt, LocalDateTime endDt) {
        this.beginDt = beginDt;
        this.endDt = endDt;
    }
}
