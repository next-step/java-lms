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

    public boolean canBeDeletedBy(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public List<DeleteHistory> delete(NsUser user) throws CannotDeleteException {
        validateDeletableBy(user);
        this.setDeleted(true);

        List<DeleteHistory> deletedHistories = answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
        deletedHistories.add(0, DeleteHistory.ofQuestion(this, user));

        return deletedHistories;
    }

    private void validateDeletableBy(NsUser user) throws CannotDeleteException {
        if (!this.canBeDeletedBy(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        if (hasOtherUsersAnswer(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean hasOtherUsersAnswer(NsUser user) {
        return answers.stream()
                .anyMatch(answer -> !answer.canBeDeletedBy(user));
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
}
