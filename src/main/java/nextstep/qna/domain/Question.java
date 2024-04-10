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

    private Answers answers = new Answers();

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


    //TODO: Answers구현 후, 수정 필요.
    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> histories = new ArrayList<>();
        assertSameUser(loginUser);
        assertNotDelete();

        histories.add(DeleteHistoryFactory.deleteHistoryForQuestion(loginUser, this));
        this.deleted = true;

        histories.addAll(answers.deleteAnswers(loginUser));
        return histories;
    }

    private void assertSameUser(NsUser loginUser) throws CannotDeleteException {
        String errorMessage = "질문을 삭제할 권한이 없습니다.";

        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException(errorMessage);
        }
    }

    private void assertNotDelete() throws CannotDeleteException {
        String errorMessage = "이미 삭제된 질문입니다.";
        if (isDeleted()) {
            throw new CannotDeleteException(errorMessage);
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers.getAnswers();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
