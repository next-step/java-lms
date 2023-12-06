package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistory {
    private Long id;

    private ContentType contentType;

    private Contents contents;

    private LocalDateTime createdDate = LocalDateTime.now();

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Contents contents, LocalDateTime createdDate) {
        this.contentType = contentType;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public List<DeleteHistory> makeDeleteHistorys(Answers answers, Contents contents, NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, contents, LocalDateTime.now()));
        for (Answer answer : answers.getAnswers()) {
            DeleteHistory deleteHistory = answer.delete(loginUser);
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contents);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contents.getId() + ", deletedBy="
                + contents.getWriter() + ", createdDate=" + createdDate + "]";
    }
}
