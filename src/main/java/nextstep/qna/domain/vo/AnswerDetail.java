package nextstep.qna.domain.vo;

import nextstep.users.domain.NsUser;

public class AnswerDetail {
    private String contents;

    private NsUser writer;

    public static AnswerDetail of(String contents, NsUser writer) {
        return new AnswerDetail(contents, writer);
    }

    private AnswerDetail(String contents, NsUser writer) {
        this.contents = contents;
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser loginUser) {
        return this.writer.equals(loginUser);
    }
}
