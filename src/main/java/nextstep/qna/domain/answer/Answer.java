package nextstep.qna.domain.answer;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.BaseTime;
import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUser;

public class Answer extends BaseTime {
    private Long id;
    private final AnswerInfo answerInfo;
    private boolean deleted = false;

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

        this.answerInfo = new AnswerInfo(question, contents, writer);
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isNotOwner(NsUser writer) {
        return !answerInfo.isOwner(writer);
    }

    public NsUser getWriter() {
        return answerInfo.getWriter();
    }

    public void deleted() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Answer{" +
            "id=" + id +
            ", answerInfo=" + answerInfo +
            ", deleted=" + deleted +
            '}';
    }
}
