package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Content {
    private String title;
    private String contents;
    private NsUser writer;

    public Content(String contents, NsUser writer) {
        this("", contents, writer);
    }

    public Content(String title, String contents, NsUser writer) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isOwner(NsUser other) {
        return this.writer.equals(other);
    }
}
