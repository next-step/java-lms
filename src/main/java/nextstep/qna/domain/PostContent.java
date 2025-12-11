package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class PostContent {
    private final NsUser writer;
    private final String title;
    private final String contents;

    public PostContent(NsUser writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public NsUser getWriter() {
        return this.writer;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }
}
