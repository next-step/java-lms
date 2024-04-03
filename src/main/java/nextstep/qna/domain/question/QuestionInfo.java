package nextstep.qna.domain.question;

import nextstep.users.domain.NsUser;

public class QuestionInfo {
    private String title;
    private String contents;
    private NsUser writer;

    private QuestionInfo() {}

    public QuestionInfo(NsUser writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
            "title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            ", writer=" + writer +
            '}';
    }
}
