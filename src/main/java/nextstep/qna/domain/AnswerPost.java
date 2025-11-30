package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class AnswerPost {
    private final NsUser writer;
    private final String contents;

    public AnswerPost(NsUser writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    public boolean isOwner(NsUser user) {
        return writer.equals(user);
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }
}
