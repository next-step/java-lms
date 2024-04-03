package nextstep.qna.domain.answer;

import nextstep.qna.domain.question.Question;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class AnswerInfo {
    private Question question;
    private String contents;
    private NsUser writer;

    private AnswerInfo() {}

    public AnswerInfo(Question question, String contents, NsUser writer) {
        this.question = question;
        this.contents = contents;
        this.writer = writer;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return this.writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerInfo that = (AnswerInfo) o;
        return Objects.equals(question, that.question) && Objects.equals(contents, that.contents) && Objects.equals(writer, that.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, contents, writer);
    }
}
