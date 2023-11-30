package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question( Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    protected void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime deletedDate() {
        return this.deletedDate;
    }

    public void delete(NsUser loginUser, LocalDateTime now) {
        validateOtherWriter(loginUser);

        this.deleted = true;
        this.deletedDate = now;
        this.answers.deleteAll(loginUser, now);
    }

    private void validateOtherWriter(NsUser loginUser) {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public List<DeleteHistory> createDeleteHistories() {
        if (deleted) {
            List<DeleteHistory> deleteHistories = answers.deleteHistories();
            deleteHistories.add(0, new DeleteHistory(ContentType.QUESTION, id, writer, deletedDate));

            return deleteHistories;
        }

        throw new IllegalArgumentException("해당 질문은 삭제되지 않았습니다. 질문 ID :: " + id);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
