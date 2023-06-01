package nextstep;

import java.time.LocalDateTime;

public class BaseTime {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public BaseTime() {
        this.createdDate = LocalDateTime.now();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
