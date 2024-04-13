package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class AnswerDetails {

    private final Long id;

    private final NsUser writer;

    private final String contents;

    public AnswerDetails(Long id, NsUser writer, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        this.writer = writer;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public String getContents() {
        return contents;
    }
}
