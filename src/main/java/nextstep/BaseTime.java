package nextstep;

import java.time.LocalDateTime;
import java.util.Objects;

public class BaseTime {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public BaseTime() {
        this.createdDate = LocalDateTime.now();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTime baseTime = (BaseTime) o;
        return Objects.equals(createdDate, baseTime.createdDate) && Objects.equals(updatedDate, baseTime.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdDate, updatedDate);
    }
}

