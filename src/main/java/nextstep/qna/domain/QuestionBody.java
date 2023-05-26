package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class QuestionBody {

    private NsUser writer;

    private String title;

    private String contents;

    public QuestionBody(final NsUser writer, final String title, final String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public void checkOwner(final NsUser loginUser) throws CannotDeleteException {
        if (writer != loginUser) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public NsUser getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "QuestionBody{" +
            "title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            '}';
    }
}
