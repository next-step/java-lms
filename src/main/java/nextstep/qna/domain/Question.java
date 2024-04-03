package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseEntity{

    private QuestionInfo questionInfo;

    private final Answers answers = new Answers(new ArrayList<>());

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        questionInfo = new QuestionInfo(writer, title, contents);
    }

    public Question(Long id, QuestionInfo questionInfo) {
        this.id = id;
        this.questionInfo = questionInfo;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    private void validateDeletable(NsUser loginUser) throws CannotDeleteException {
        if (questionInfo.isNotOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        answers.validateDeletable(loginUser);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateDeletable(loginUser);
        deleted = true;
        List<DeleteHistory> deleteAnswerHistories = answers.delete();
        return deletedHistories(deleteAnswerHistories);
    }

    private List<DeleteHistory> deletedHistories(List<DeleteHistory> deleteHistories) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, questionInfo.getWriter(), LocalDateTime.now()));
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + " " + questionInfo + " ]";
    }
}
