package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

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
        this.answers = new Answers();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        try {
            validateDelete(loginUser);
            LocalDateTime deleteTime = LocalDateTime.now();

            List<DeleteHistory> deleteHistories = new ArrayList<>();
            deleteHistories.add(delete(deleteTime));
            deleteHistories.addAll(deleteAnswers(loginUser, deleteTime));

            return deleteHistories;
        } catch (UnAuthorizedException e) {
            throw new CannotDeleteException(e.getMessage());
        }
    }

    private List<DeleteHistory> deleteAnswers(NsUser loginUser, LocalDateTime deleteTime) throws CannotDeleteException {
        try {
            return this.answers.deleteAnswers(loginUser, deleteTime);
        } catch (UnAuthorizedException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

    }

    private DeleteHistory delete(LocalDateTime deleteTime) {
        this.deleted = true;
        return DeleteHistory.createQuestionHistory(this.id, this.writer, deleteTime);
    }

    private void validateDelete(NsUser loginUser) {
        if (!this.writer.equals(loginUser)) {
            throw new UnAuthorizedException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
