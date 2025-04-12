package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private static final String QUESTION_DELETE_UNAUTHORIZED = "질문을 삭제할 권한이 없습니다.";
    private static final String QUESTION_DELETE_FORBIDDEN_OTHERS_ANSWER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private final Answers answers = new Answers();

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

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        this.validateDeletableBy(loginUser);
        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.deleteAll());
        return deleteHistories;
    }

    private void validateDeletableBy(NsUser loginUser) throws CannotDeleteException {
        if (writer.isNotEqual(loginUser)) {
            throw new CannotDeleteException(QUESTION_DELETE_UNAUTHORIZED);
        }
        if (answers.hasNotMatchedOwner(loginUser)) {
            throw new CannotDeleteException(QUESTION_DELETE_FORBIDDEN_OTHERS_ANSWER);
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
