package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class AnswerContent {

    private NsUser writer;

    private String contents;

    public AnswerContent(NsUser writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    public NsUser writer() {
        return writer;
    }

    public boolean equalsWriter(NsUser loginUser) {
        return writer.equals(loginUser);
    }
}

