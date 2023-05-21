package nextstep.qna.domain;

import nextstep.qna.exception.QuestionDeleteAnswerExistedException;
import nextstep.qna.exception.QuestionDeleteUnauthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private final List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question() {
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

    public void addAnswer(Answer answer) {
        answer.relateToQuestion(this);
        answers.add(answer);
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
        deleteQuestionAndAnswers();
        return makeDeleteHistories();
    }

    private void deleteQuestionAndAnswers() {
        this.doDelete();
        answers.forEach(Answer::doDelete);
    }

    private void doDelete() {
        this.deleted = true;
    }

    private List<DeleteHistory> makeDeleteHistories() {
        List<DeleteHistory> deleteHistories = answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
        deleteHistories.add(this.toDeleteHistory());
        return deleteHistories;
    }

    private void validateDelete(NsUser loginUser) {
        validateQuestionOwner(loginUser);
        validateAnswers(loginUser);
    }

    private void validateAnswers(NsUser loginUser) {
        answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny().ifPresent(answer -> {
                    throw new QuestionDeleteAnswerExistedException();
                });
    }

    private void validateQuestionOwner(NsUser loginUser) {
        if (!writer.matchUser(loginUser)) {
            throw new QuestionDeleteUnauthorizedException();
        }
    }

    public DeleteHistory toDeleteHistory() {
        return DeleteHistory.of(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
    }
}
