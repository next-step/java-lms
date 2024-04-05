package nextstep.qna.domain.question;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.BaseTime;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.qna.domain.deleteHistory.DeleteHistory;
import nextstep.qna.domain.deleteHistory.type.ContentType;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Question extends BaseTime {

    public static final String NOT_PERMISSION_DELETE_QUESTION = "질문을 삭제할 권한이 없습니다.";
    private final Long id;
    private final QuestionInfo questionInfo;
    private final Answers answers;
    private boolean deleted = false;

    public Question(Long id, NsUser writer, String title, String contents, Answers answers) {
        this.id = id;
        this.questionInfo = new QuestionInfo(writer, title, contents);
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return questionInfo.getWriter();
    }

    public Answers getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {

        if (isOwner(loginUser)) {
            answers.validateDeleteIsPossible(questionInfo.getWriter());
            this.deleted = true;
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, questionInfo.getWriter()));
        deleteHistories.addAll(answers.deleteAll(questionInfo.getWriter()));

        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    private boolean isOwner(NsUser loginUser) throws CannotDeleteException {
        if (!questionInfo.isOwner(loginUser)) {
            throw new CannotDeleteException(NOT_PERMISSION_DELETE_QUESTION);
        }

        return questionInfo.isOwner(loginUser);
    }

    @Override
    public String toString() {
        return "Question{" +
            "questionInfo=" + questionInfo +
            ", answers=" + answers +
            ", deleted=" + deleted +
            '}';
    }
}
