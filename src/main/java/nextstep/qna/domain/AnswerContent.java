package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class AnswerContent {
    private Content content;

    public AnswerContent(String contents, NsUser writer) {
        this(new Content(contents, writer));
    }

    public AnswerContent(Content content) {
        this.content = content;
    }

    public NsUser getWriter() {
        return this.content.getWriter();
    }

    public String getContents() {
        return this.content.getContents();
    }

    public boolean isOwner(NsUser other) {
        return this.content.isOwner(other);
    }
}
