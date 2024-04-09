package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseEntity  {

    private AnswerMetaData answerMetaData;
    private Question question;

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

        answerMetaData = new AnswerMetaData(writer, contents);
    }

    public boolean isOwner(NsUser loginUser) {
        return answerMetaData.isOwner(loginUser);
    }

    public NsUser getWriter() {
        return answerMetaData.getWriter();
    }


    public void toQuestion(Question question) {
        this.question = question;
    }

    public void validateDeletable(NsUser requester) throws CannotDeleteException {
        if (!isOwner(requester)) {
            throw new CannotDeleteException("자신의 작성한 답변만 삭제할 수 있습니다.");
        }
    }

    public DeleteHistory delete() {
        deleted();
        return new DeleteHistory(ContentType.ANSWER, id, answerMetaData.getWriter(), LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", " + answerMetaData + " ]";
    }
}
