package nextstep.qna.domain;

public class BoardContent {
    private String title;
    private String contents;

    public BoardContent(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "BoardContent{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
