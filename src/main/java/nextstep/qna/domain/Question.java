package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static nextstep.qna.domain.ContentType.*;

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

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser, long questionId) throws CannotDeleteException {
        validateLoginUser(loginUser);
        validateAnswerUser(loginUser);

        convertStatusToDeleted();
        convertAnswerStatusToDeleted();

        return deleteHistories(questionId);
    }

    private List<DeleteHistory> deleteHistories(long questionId) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(questionDeleteHistory(questionId));
        deleteHistories.addAll(answerDeleteHistories());

        return Collections.unmodifiableList(deleteHistories);
    }

    private DeleteHistory questionDeleteHistory(long questionId) {
        return new DeleteHistory(QUESTION, questionId, this.writer, LocalDateTime.now());
    }

    private List<DeleteHistory> answerDeleteHistories() {
        return answers.stream()
                .map(answer -> new DeleteHistory(ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void convertStatusToDeleted() {
        deleted = true;
    }

    private void convertAnswerStatusToDeleted() {
        answers = answers.stream()
                .map(answer -> answer.setDeleted(true))
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateAnswerUser(NsUser loginUser) throws CannotDeleteException {
        if(containsNotOwnedAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean containsNotOwnedAnswer(NsUser loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    private void validateLoginUser(NsUser loginUser) throws CannotDeleteException {
        if(!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
