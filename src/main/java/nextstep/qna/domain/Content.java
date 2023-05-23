package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Content {

    private String title;

    private String contents;

    private NsUser writer;

    private boolean deleted = false;

    public Content(String title, String contents, NsUser writer) {
        writerValidate(writer);
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public Content(String contents, NsUser writer) {
        writerValidate(writer);
        this.contents = contents;
        this.writer = writer;
    }

    private void writerValidate(NsUser writer) {
        if(writer == null) {
            throw new UnAuthorizedException();
        }
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Content [title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
