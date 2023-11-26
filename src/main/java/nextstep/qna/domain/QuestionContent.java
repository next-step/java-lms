package nextstep.qna.domain;

public class QuestionContent {

    private String title;

    private String contents;


    public QuestionContent( String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "QuestionContent [" + "title='" + title + '\'' + ", contents='" + contents + '\'' + ']';
    }
}
