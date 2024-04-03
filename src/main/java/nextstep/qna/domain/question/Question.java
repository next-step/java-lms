package nextstep.qna.domain.question;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.BaseTime;
import nextstep.qna.domain.answer.Answers;
import nextstep.qna.domain.generator.RandomIdGenerator;
import nextstep.users.domain.NsUser;

public class Question extends BaseTime {

    public static final String NOT_PERMISSION_DELETE_QUESTION = "질문을 삭제할 권한이 없습니다.";
    private final Long id;
    private final QuestionInfo questionInfo;
    private boolean deleted = false;

    public Question(NsUser writer, String title, String contents) {
        this.id = RandomIdGenerator.generate();
        this.questionInfo = new QuestionInfo(writer, title, contents);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return questionInfo.getWriter();
    }

    public void delete(NsUser loginUser, Answers answers) throws CannotDeleteException {
        if (isOwner(loginUser)) {
            answers.validateDeleteIsPossible(questionInfo.getWriter());
            this.deleted = true;
        }
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
            "id=" + id +
            ", questionInfo=" + questionInfo +
            ", deleted=" + deleted +
            ", createdDate=" + createdDate +
            ", updatedDate=" + updatedDate +
            '}';
    }
}
