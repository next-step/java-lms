package nextstep.qna.domain.vo;

import nextstep.users.domain.NsUser;

public class QuestionDetail {
    private String title;

    private String contents;

    private NsUser writer;

    public static QuestionDetail of(String title, String contents, NsUser writer) {
        return new QuestionDetail(title, contents, writer);
    }

    private QuestionDetail(String title, String contents, NsUser writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
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
