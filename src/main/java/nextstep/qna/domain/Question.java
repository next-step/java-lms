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

    private final Answers answers = new Answers();

    private boolean deleted = false;

    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    protected Question() {
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

    public void delete(NsUser user) throws CannotDeleteException {
        deleteQuestion(user);
        deleteAnswers(user);
    }

    private void deleteQuestion(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        this.deleted = true;
    }

    private void deleteAnswers(NsUser user) throws CannotDeleteException {
        this.answers.delete(user);
    }

    public List<DeleteHistory> toHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        this.addTo(deleteHistories);
        this.answers.addTo(deleteHistories);
        return deleteHistories;
    }

    private void addTo(List<DeleteHistory> deleteHistories) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
