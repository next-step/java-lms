package nextstep.qna.domain;

import java.util.Objects;

public class QuestionArticle {

    private final QuestionTitle title;

    private final QuestionContent contents;

    public QuestionArticle(String title, String contents) {
        this(new QuestionTitle(title), new QuestionContent(contents));
    }

    public QuestionArticle(QuestionTitle title, QuestionContent contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionArticle that = (QuestionArticle) o;
        return Objects.equals(title, that.title) && Objects.equals(contents,
                that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }

    @Override
    public String toString() {
        return "QuestionArticle{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
