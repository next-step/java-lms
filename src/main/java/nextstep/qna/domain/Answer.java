package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends SoftDeletableModel {
    private Long id;

    private NsUser writer;

    private Question question;

    private QuestionBody questionBody;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, new QuestionBody(contents));
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
       this(id, writer, question, new QuestionBody(contents));
    }

    public Answer(Long id, NsUser writer, Question question, QuestionBody questionBody) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.questionBody = questionBody;
    }

    public Long getId() {
        return id;
    }



    public boolean isDeleted() {
        return getDeleted();
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }


    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        updateDeleted();
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + questionBody.getContents() + "]";
    }
}
