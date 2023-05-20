package nextstep.qna.domain;

import nextstep.qna.exception.QuestionDeleteAnswerExistedException;
import nextstep.qna.exception.QuestionDeleteUnauthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(NsUser loginUser) {
        validateDelete(loginUser);
        return makeDeleteHistories();
    }

    private List<DeleteHistory> makeDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        this.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.getWriter(), LocalDateTime.now()));
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    private void validateDelete(NsUser loginUser) {
        validateQuestionOwner(loginUser);
        validateAnswers(loginUser);
    }

    private void validateAnswers(NsUser loginUser) {
        for (Answer answer : this.answers) {
            if (!answer.isOwner(loginUser)) {
                throw new QuestionDeleteAnswerExistedException();
            }
        }
    }

    private void validateQuestionOwner(NsUser loginUser) {
        if (isOwner(loginUser)) {
            throw new QuestionDeleteUnauthorizedException();
        }
    }
}
