package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class AnswerBuilder {
    private Question question;
    private NsUser writer;
    private String content;

    private AnswerBuilder() {
    }

    public static AnswerBuilder aAnswer() {
        return new AnswerBuilder();
    }

    public AnswerBuilder withQuestion(Question question) {
        this.question = question;
        return this;
    }

    public AnswerBuilder withWriter(NsUser writer) {
        this.writer = writer;
        return this;
    }

    public AnswerBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public Answer build() {
        Answer answer = new Answer(writer, question, content);
        return answer;
    }
}
