package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ContentTimestamp {
    private final LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContentTimestamp that = (ContentTimestamp) o;
        return Objects.equals(createdDate, that.createdDate) && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdDate, updatedDate);
    }
}
