package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class ContentDetails {
    private final NsUser writer;
    private final String contents;

    public ContentDetails(NsUser writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    public boolean isWrittenBy(NsUser user) {
        return writer.equals(user);
    }

    public NsUser getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "ContentDetails{writer=" + writer + ", contents='" + contents + '\'' + '}';
    }
}
