package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private CreatedDateTimeProvider createdDateTimeProvider;

    public Question() {
    }

    public Question(CreatedDateTimeProvider createdDateTimeProvider, NsUser writer, String title, String contents) {
        this(createdDateTimeProvider, 0L, writer, title, contents);
    }

    public Question(CreatedDateTimeProvider createdDateTimeProvider, Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDateTimeProvider = createdDateTimeProvider;
        this.createdDate = createdDateTimeProvider.now();
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, getWriter(), createdDateTimeProvider.now()));
        deleteHistories.addAll(answers.delete(loginUser));
        deleted = true;

        return deleteHistories;
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
}
