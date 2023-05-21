package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static nextstep.qna.domain.ContentType.*;

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

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(NsUser loginUser, long questionId) throws CannotDeleteException {
        validateLoginUser(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        LocalDateTime deleteHistoryCreatedDate = LocalDateTime.now();
        deleteHistories.add(deleteQuestion(questionId, deleteHistoryCreatedDate));
        deleteHistories.addAll(deleteAnswer(loginUser, deleteHistoryCreatedDate));

        return Collections.unmodifiableList(deleteHistories);
    }

    private DeleteHistory deleteQuestion(long questionId, LocalDateTime createdDate) {
        deleted = true;
        return new DeleteHistory(QUESTION, questionId, this.writer, createdDate);
    }

    private List<DeleteHistory> deleteAnswer(NsUser loginUser, LocalDateTime createdDate) throws CannotDeleteException {
        return answers.delete(loginUser, createdDate);
    }

    private void validateLoginUser(NsUser loginUser) throws CannotDeleteException {
        if(!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
