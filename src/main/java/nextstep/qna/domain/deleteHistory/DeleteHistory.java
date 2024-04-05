package nextstep.qna.domain.deleteHistory;

import nextstep.qna.domain.BaseTime;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.qna.domain.deleteHistory.type.ContentType;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistory extends BaseTime {
    private Long id;
    private DeleteHistoryContent content;
    private NsUser deletedBy;

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        this.content = new DeleteHistoryContent(contentId, contentType);
        this.deletedBy = deletedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory{" +
            "id=" + id +
            ", content=" + content +
            ", deletedBy=" + deletedBy +
            '}';
    }
}
