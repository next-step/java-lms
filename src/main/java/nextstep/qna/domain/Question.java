package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseEntity {
    private Long id;

    private String title;

    private String contents;

    private User writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(User writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, User writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public User getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(createDeleteHistory());
        deleteHistories.addAll(answers.deleteAll(loginUser));

        this.deleted = true;

        return deleteHistories;
    }

    private DeleteHistory createDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }

    private boolean isOwner(User loginUser) {
        return this.writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
