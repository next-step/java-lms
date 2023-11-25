package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = Answers.init();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers = answers.added(answer);
    }

    public List<DeleteHistory> deleted(NsUser loginUser) {
        validateDelete(loginUser);
        delete();
        return deletedHistory();
    }

    public void validateDelete(NsUser loginUser) {
        validateIsOwner(loginUser);
        validateAnswerOfOthers();
    }

    private void validateIsOwner(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void validateAnswerOfOthers() {
        if (hasAnswerOfOthers()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(NsUser loginUser) {
        return this.writer.equals(loginUser);
    }

    private boolean hasAnswerOfOthers() {
        return this.answers.hasAnswerExcept(this.writer);
    }

    private void delete() {
        this.deleted = true;
    }

    private List<DeleteHistory> deletedHistory() {
        List<DeleteHistory> result = new ArrayList<>();
        result.add(deletedQuestion());
        result.addAll(deletedAnswer());

        return result;
    }

    private DeleteHistory deletedQuestion() {
        return DeleteHistory.question(this.id, this.writer);
    }

    private List<DeleteHistory> deletedAnswer() {
        return this.answers.deleted();
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
