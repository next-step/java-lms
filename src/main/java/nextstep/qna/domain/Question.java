package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
    private Long id;

    private TextBody textBody;

    private String title;

    private Answers answers;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(TextBody textBody, String title) {
        this(0L, textBody, title);
    }

    public Question(Long id, TextBody textBody, String title) {
        this.answers = new Answers(new ArrayList<>());
        this.id = id;
        this.textBody = textBody;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public TextBody getTextBody() {
        return textBody;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        if (!this.textBody.isOwner(loginUser)) {
            throw new UnAuthorizedException("질문을 삭제할 권한이 없습니다.");
        }

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(DeleteHistory.ofQuestion(ContentType.QUESTION, deletedQuestion(), LocalDateTime.now()));

        return deleteHistories.addAll(new Answers(answers.delete(loginUser)));
    }

    private Question deletedQuestion() {
        this.textBody.deleted();
        return new Question(this.id, TextBody.of(textBody), this.title);
    }

    public void isNull() {
        if (this == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Question question = (Question)o;
        return Objects.equals(id, question.id) && Objects.equals(textBody, question.textBody)
            && Objects.equals(title, question.title) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textBody, title, answers);
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", textBody=" + textBody +
            ", title='" + title + '\'' +
            '}';
    }
}
