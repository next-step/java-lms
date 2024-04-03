package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionDetails {

    private Long id;

    private NsUser writer;

    private String title;

    private String contents;

    public QuestionDetails() {
    }

    public QuestionDetails(Long id, NsUser writer, String title, String contents) {
        this.id = 0L;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
