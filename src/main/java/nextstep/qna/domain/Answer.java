package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

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

    public Answer setDeleted() {
        textBody = TextBody.of(textBody);
        return this;
    }

    public TextBody getTextBody() {
        return textBody;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void compareUser(NsUser loginUser) {
        UserComparison userComparison = (textBody, writer) -> {
            if (textBody.isNotOwner(writer)) {
                throw new UnAuthorizedException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        };
        userComparison.compare(this.textBody, loginUser);
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
