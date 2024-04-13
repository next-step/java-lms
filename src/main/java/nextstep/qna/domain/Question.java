package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private RelatedAnswers relatedAnswers;

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
        this.relatedAnswers = new RelatedAnswers();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        relatedAnswers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public void deleteAll(NsUser loginUser) throws CannotDeleteException {
        this.delete(loginUser);
        relatedAnswers.deleteAll(loginUser);
    }

    private void delete(NsUser loginUser) throws CannotDeleteException {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        this.deleted = true;
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(this.toDeleteHistory());
        deleteHistories.addAll(relatedAnswers.toDeleteHistories());
        return deleteHistories;
    }

    private DeleteHistory toDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(id, question.id) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(relatedAnswers, question.relatedAnswers) && Objects.equals(createdDate, question.createdDate) && Objects.equals(updatedDate, question.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, writer, relatedAnswers, deleted, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
