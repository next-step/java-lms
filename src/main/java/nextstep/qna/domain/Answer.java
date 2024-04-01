package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseEntity  {

    private AnswerInfo answerInfo;
    private Question question;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }
        this.question = question;

        answerInfo = new AnswerInfo(writer, contents);
    }

    private boolean isNotOwner(NsUser loginUser) {
        return answerInfo.isNotOwner(loginUser);
    }

    public NsUser getWriter() {
        return answerInfo.getWriter();
    }


    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", " + answerInfo + " ]";
    }

    public void validateDeletable(NsUser loginUser) throws CannotDeleteException {
        if (isNotOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public DeleteHistory delete() {
        deleted = true;
        return new DeleteHistory(ContentType.ANSWER, id, answerInfo.getWriter(), LocalDateTime.now());
    }
}
