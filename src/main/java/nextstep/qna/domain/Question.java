package nextstep.qna.domain;

import nextstep.common.BaseEntity;
import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question extends BaseEntity {
    private final Long id;

    private final NsUser writer;

    private QuestionContents questionContents;

    private Answers answers = new Answers(this);

    private boolean deleted = false;

    public Question(Long id, NsUser writer, QuestionContents questionContents) {
        this.id = id;
        this.writer = writer;
        this.questionContents = questionContents;
    }

    public void delete(Long loginUserId) throws CannotDeleteException {
        validateIfQuestionerIsSameAsLoginUser(loginUserId);
        deleteAnswers();
        deleteQuestion();
    }

    private void deleteQuestion() {
        this.deleted = true;
    }

    private void deleteAnswers() throws CannotDeleteException {
        answers.deleteAll();
    }

    private void validateIfQuestionerIsSameAsLoginUser(Long loginUserId) throws CannotDeleteException {
        if (!writer.matchId(loginUserId)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteHistory toHistory() {
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }

    public DeleteHistories toQuestionAndAnswersHistories() {
        DeleteHistories deleteHistories = new DeleteHistories();

        deleteHistories.addDeleteHistory(toHistory());
        deleteHistories.addDeleteHistories(answers.toHistories());

        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public Long getWriterId() {
        return writer.getId();
    }

    public Answers getAnswers() {
        return answers;
    }
}
