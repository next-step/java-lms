package nextstep.qna.domain;

import java.time.LocalDateTime;

public class AuditInfo {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public AuditInfo() {
        this(LocalDateTime.now(), null);
    }

    public AuditInfo(LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String toString() {
        return "AuditInfo [createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
    }
}
