package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class AnswerContent {
    private NsUser writer;
    private String contents;

    public AnswerContent(NsUser writer, String contents) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        this.writer = writer;
        this.contents = contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public boolean isOwner(NsUser other) {
        return this.writer.equals(other);
    }
}
