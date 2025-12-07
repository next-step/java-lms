package nextstep.qna.domain;

public class QuestionContent {

    private final String title;

    private final String contents;

    public QuestionContent(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String title() {
        return title;
    }

    public String contents() {
        return contents;
    }

    @Override
    public String toString() {
        return "QuestionContent{" +
                ", title=" + title + ", contents=" + contents +
                '}';
    }
}
