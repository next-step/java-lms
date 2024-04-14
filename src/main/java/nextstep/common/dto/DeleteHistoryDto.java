package nextstep.common.dto;

import java.time.LocalDateTime;

public class DeleteHistoryDto {

    private final long id;
    private final long contentId;
    private final String contentType;
    private final LocalDateTime createdAt;
    private final long deletedById;

    public DeleteHistoryDto(long id, long contentId, String contentType, LocalDateTime createdAt, long deletedById) {
        this.id = id;
        this.contentId = contentId;
        this.contentType = contentType;
        this.createdAt = createdAt;
        this.deletedById = deletedById;
    }

    public long getId() {
        return id;
    }

    public long getContentId() {
        return contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getDeletedById() {
        return deletedById;
    }
}
