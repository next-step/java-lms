package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.collection.Answers;
import nextstep.qna.domain.collection.DeleteHistoryBulk;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
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

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public DeleteHistoryBulk deleteIfWriter(NsUser writer) {
        DeleteHistoryBulk deleteHistories = deleteAnswers(writer);
        DeleteHistory deleteHistory = deleteQuestion(writer);

        deleteHistories.addAtFisrt(deleteHistory);

        return deleteHistories;
    }

    private DeleteHistoryBulk deleteAnswers(NsUser writer) {
        return this.answers.markDeletionAllIfWriter(writer);
    }

    private DeleteHistory deleteQuestion(NsUser writer) {
        if (!isOwner(writer)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, this.id, writer);
    }

}