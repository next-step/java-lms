package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.dto.DurationDTO;

public class Duration {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;


    public Duration(LocalDateTime startDate, LocalDateTime endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException(ExceptionMessage.DURATION_RANGE.getMessage());
        }
    }

    public DurationDTO toDto(){
        return new DurationDTO(startDate, endDate);
    }
}
