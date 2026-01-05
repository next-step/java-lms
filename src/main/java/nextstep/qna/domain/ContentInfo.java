package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class ContentInfo {
    private final ContentBody body;
    private final ContentStatus status;

    public ContentInfo(NsUser writer, String contents) {
        this(new ContentBody(writer, contents));
    }

    public ContentInfo(ContentBody body) {
        this(body, new ContentStatus());
    }

    public ContentInfo(ContentBody body, ContentStatus status) {
        this.body = body;
        this.status = status;
    }

    public boolean isDeleted() {
        return status.isDeleted();
    }

    public boolean isOwner(NsUser loginUser) {
        return body.isOwner(loginUser);
    }

    public NsUser getWriter() {
        return body.getWriter();
    }

    public void delete() {
        status.delete();
    }
}
