package nextstep.qna.domain.deleteHistory;

import nextstep.qna.domain.deleteHistory.type.ContentType;

import java.util.Objects;

public class DeleteHistoryContent {
    private Long contentId;
    private ContentType contentType;

    private DeleteHistoryContent() {}

    public DeleteHistoryContent(Long contentId, ContentType contentType) {
        this.contentId = contentId;
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistoryContent that = (DeleteHistoryContent) o;
        return Objects.equals(contentId, that.contentId) && contentType == that.contentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, contentType);
    }

    @Override
    public String toString() {
        return "DeleteHistoryContent{" +
            "contentId=" + contentId +
            ", contentType=" + contentType +
            '}';
    }
}
