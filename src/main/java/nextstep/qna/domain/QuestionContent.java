package nextstep.qna.domain;

public class QuestionContent {

    private String title;
    private String contents;

    public QuestionContent(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String title() {
        return this.title;
    }

    public String contents() {
        return this.contents;
    }
}
