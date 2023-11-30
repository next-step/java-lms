package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final Long id;

    private final String title;

    private final String contents;

    private final NsUser writer;

    private Answers answers;

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = Answers.initialize();
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
        this.answers = answers.addAnswer(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateQuestionAndAnswerDeleteOwner(loginUser);

        return deleteAndSaveHistories();
    }

    private List<DeleteHistory> deleteAndSaveHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.addAll(deleteAnswers());

        deleteQuestion();
        deleteHistories.add(DeleteHistory.Question(id, writer));
        return deleteHistories;
    }

    private void validateQuestionAndAnswerDeleteOwner(NsUser loginUser) throws CannotDeleteException {
        validateQuestionDeleteOwner(loginUser);
        validateAnswersDeleteOwner();
    }

    private void validateQuestionDeleteOwner(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void validateAnswersDeleteOwner() throws CannotDeleteException {
        if (answers.validateDeleteOwner(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private void deleteQuestion() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    private List<DeleteHistory> deleteAnswers() {
        return answers.deleteAll();
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
