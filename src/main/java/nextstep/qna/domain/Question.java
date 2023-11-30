package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
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

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
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

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public DeleteHistory deleteQuestion(NsUser userId) throws CannotDeleteException {
        if (!isOwner(userId)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        setDeleted(true);
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now());
    }

    public List<DeleteHistory> delete(NsUser userId) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = deleteAnswers(userId);
        DeleteHistory questionDeleteHistory = deleteQuestion(userId);
        deleteHistories.add(questionDeleteHistory);
        return deleteHistories;
    }

    public List<DeleteHistory> deleteAnswers(NsUser userId) throws CannotDeleteException {
        if (!ableDeleteAnswer(userId)) {
            throw new CannotDeleteException("다른 사람이 작성한 답변이 존재해 삭제할 수 없습니다.");
        }
        return answers.stream()
                .map(answer -> answer.delete())
                .collect(Collectors.toList());
    }

    private boolean ableDeleteAnswer(NsUser userId) {
        return answers.stream().filter(answer -> !answer.isOwner(userId))
                .findAny()
                .map(i -> false)
                .orElse(true);
    }
}
