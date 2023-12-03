package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionContent {

    private String title;

    private String contents;

    private NsUser writer;

    public QuestionContent(NsUser writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public NsUser writer() {
        return writer;
    }

    public boolean equalsWriter(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public String title() {
        return title;
    }
}

