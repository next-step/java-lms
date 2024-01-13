package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private TextBody textBody;

    private String title;

    private Answers answers = new Answers(new ArrayList<>());

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(TextBody textBody, String title) {
        this(0L, textBody, title);
    }

    public Question(Long id, TextBody textBody, String title) {
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
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser, long questionId) {
        UserComparison userComparison = (textBody, user) -> {
            if (textBody.isNotOwner(user)) {
                throw new UnAuthorizedException("질문을 삭제할 권한이 없습니다.");
            }
        };
        userComparison.compare(textBody, loginUser);
        answers.checkAuthorization(loginUser);

        DeleteHistories deleteHistories = new DeleteHistories();
        textBody = TextBody.of(textBody);

        return answers.delete(deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, textBody.getWriter(), LocalDateTime.now())));
    }

    public void isNull() {
        if (this == null) {
            throw new NotFoundException();
        }
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", textBody=" + textBody +
            ", title='" + title + '\'' +
            ", answers=" + answers +
            '}';
    }
}
