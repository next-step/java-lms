package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class AnswerBody {

    private NsUser writer;

    private Question question;

    private String contents;

    public AnswerBody(final NsUser writer, final Question question, final String contents) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }
        if (question == null) {
            throw new NotFoundException();
        }
        this.writer = writer;

        this.question = question;
        this.contents = contents;
    }

    public void checkOwner(final NsUser loginUser) throws CannotDeleteException {
        if (writer != loginUser) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void addQuestion(final Question question) {
        this.question = question;
    }

    public NsUser getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "AnswerBody{" +
            "writer=" + writer +
            ", question=" + question +
            ", contents='" + contents + '\'' +
            '}';
    }
}
