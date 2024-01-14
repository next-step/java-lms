package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {
    private Long id;

    private TextBody textBody;

    private Question question;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(TextBody textBody, Question question) {
        this(null, textBody, question);
    }

    public Answer(Long id, TextBody textBody, Question question) {
        this.id = id;
        textBody.isNull();
        question.isNull();
        this.question = question;
        this.textBody = textBody;
    }

    public Long getId() {
        return id;
    }

    public Answer create() {
        textBody = TextBody.of(textBody);
        return new Answer(id, textBody, question);
    }

    public TextBody getTextBody() {
        return textBody;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public Answer delete(NsUser loginUser) {
        if (!this.textBody.isOwner(loginUser)) {
            throw new UnAuthorizedException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        textBody.deleted();
        return new Answer(id, TextBody.of(textBody), question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Answer answer = (Answer)o;
        return Objects.equals(id, answer.id) && Objects.equals(textBody, answer.textBody)
            && Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textBody, question);
    }

    @Override
    public String toString() {
        return "Answer{" +
            "id=" + id +
            ", textBody=" + textBody +
            ", question=" + question +
            '}';
    }
}
