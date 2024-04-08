package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static nextstep.qna.domain.ContentType.QUESTION;

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

    public List<DeleteHistory> deleteByUser(NsUser user) throws CannotDeleteException {
        validateUser(user);
        List<DeleteHistory> deleteHistoriesOfAnswers = answers.deleteByUser(user);
        this.deleted = true;
        return deleteHistoriesOfQuestion(deleteHistoriesOfAnswers);
    }

    private void validateUser(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOwner(NsUser user) {
        return writer.equals(user);
    }

    private List<DeleteHistory> deleteHistoriesOfQuestion(List<DeleteHistory> deleteHistoriesOfAnswers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(new DeleteHistory(QUESTION, id, writer, LocalDateTime.now()));
        deleteHistories.addAll(deleteHistoriesOfAnswers);

        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
