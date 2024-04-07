package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.ContentType.QUESTION;

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
    private List<Answer> answers = new ArrayList<>();
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

    public List<DeleteHistory> deleteBy(
            final NsUser user,
            final LocalDateTime deleteDateTime
    ) throws CannotDeleteException {
        validateUserIsWriter(user);

        deleted = true;

        final List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(QUESTION, id, writer, deleteDateTime));

        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ANSWER, answer.getId(), answer.getWriter(), deleteDateTime));
        }

        return deleteHistories;
    }

    private void validateUserIsWriter(final NsUser user) throws CannotDeleteException {
        if (!user.matchUser(writer)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
