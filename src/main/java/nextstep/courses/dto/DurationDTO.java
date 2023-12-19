package nextstep.courses.dto;

import java.time.LocalDateTime;

public class DurationDTO {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public DurationDTO(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
