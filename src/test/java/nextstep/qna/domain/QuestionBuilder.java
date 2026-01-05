package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class QuestionBuilder {
    private String title;
    private NsUser writer;
    private String contents;

    private QuestionBuilder() {
    }

    public static QuestionBuilder aQuestion() {
        return new QuestionBuilder();
    }

    public QuestionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public QuestionBuilder withWriter(NsUser writer) {
        this.writer = writer;
        return this;
    }

    public QuestionBuilder withContents(String contents) {
        this.contents = contents;
        return this;
    }

    public Question build() {
        Question question = new Question(writer, title, contents);
        return question;
    }
}
