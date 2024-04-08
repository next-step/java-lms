package nextstep.qna.domain.vo;

import java.util.Objects;

public class QuestionBody {

    private String title;

    private String contents;

    public QuestionBody() {
    }

    public QuestionBody(String title,
                        String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionBody that = (QuestionBody) o;
        return Objects.equals(title, that.title) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }
}
