package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Question extends BaseEntity{

    private QuestionMetaData questionMetaData;

    private final Answers answers = new Answers(new ArrayList<>());

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        questionMetaData = new QuestionMetaData(writer, title, contents);
    }

    public Question(Long id, QuestionMetaData questionMetaData) {
        this.id = id;
        this.questionMetaData = questionMetaData;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question [id=" + id + " " + questionMetaData + " ]";
    }

    private void validateDeletable(NsUser loginUser) throws CannotDeleteException {
        if (!questionMetaData.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        answers.validateDeletable(loginUser);
    }

    public DeleteHistories delete(NsUser loginUser) throws CannotDeleteException {
        validateDeletable(loginUser);
        deleted = true;
        DeleteHistories deleteAnswerHistories = answers.delete();
        return deletedHistories(deleteAnswerHistories);
    }

    private DeleteHistories deletedHistories(DeleteHistories deleteHistories) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, questionMetaData.getWriter(), LocalDateTime.now()));
        return deleteHistories;
    }
}
