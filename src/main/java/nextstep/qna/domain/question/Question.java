package nextstep.qna.domain.question;

import static nextstep.qna.domain.history.ContentType.QUESTION;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.history.DeleteHistory;
import nextstep.users.domain.NsUser;

public class Question {

    private final Long id;
    private final NsUser writer;
    private final String title;
    private final String contents;
    private final Answers answers;
    private final LocalDateTime createdDate;
    private boolean deleted;
    private LocalDateTime updatedDate;

    public Question(final NsUser writer, final String title, final String contents) {
        this(0L, writer, title, contents);
    }

    public Question(final Long id, final NsUser writer, final String title, final String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
        this.createdDate = LocalDateTime.now();
        this.deleted = false;
    }

    public Long id() {
        return this.id;
    }

    public NsUser writer() {
        return this.writer;
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
        this.answers.add(answer);
    }

    public boolean isOwner(final NsUser loginUser) {
        return this.writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public List<DeleteHistory> deleteBy(
            final NsUser loginUser,
            final LocalDateTime deleteDateTime
    ) throws CannotDeleteException {

        validateLoginUserIsWriter(loginUser);

        final List<DeleteHistory> deleteHistories = new ArrayList<>();
        final DeleteHistory questionDeleteHistory = new DeleteHistory(QUESTION, this.id, this.writer, deleteDateTime);
        deleteHistories.add(questionDeleteHistory);
        final List<DeleteHistory> answersDeleteHistories = this.answers.delete(this.writer, deleteDateTime);
        deleteHistories.addAll(answersDeleteHistories);

        this.deleted = true;

        return deleteHistories;
    }

    private void validateLoginUserIsWriter(final NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + id() + ", title=" + this.title + ", contents=" + this.contents + ", writer="
                + this.writer + "]";
    }
}
