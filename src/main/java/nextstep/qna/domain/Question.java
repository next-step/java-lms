package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question extends BaseEntity {
    private static final String DELETE_ERROR_MESSAGE = "질문을 삭제할 권한이 없습니다.";

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

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

    public Answers getAnswers() {
        return answers;
    }

    private boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public List<DeleteHistory> delete(NsUser loginUser, long questionId) throws CannotDeleteException {
        validateLoginUser(loginUser);

        convertStatusToDeleted();

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, this.writer, LocalDateTime.now()));
        deleteHistories.addAll(this.answers.delete(loginUser));

        return Collections.unmodifiableList(deleteHistories);
    }

    private void validateLoginUser(NsUser loginUser) throws CannotDeleteException {
        if(!isOwner(loginUser)) {
            throw new CannotDeleteException(DELETE_ERROR_MESSAGE);
        }
    }

    private void convertStatusToDeleted() {
        this.deleted = true;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
