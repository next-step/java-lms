package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    public static final String DELETE_ANSWERS_AUTHORITY = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
    public static final String DELETE_QUESTION_AUTHORITY = "질문을 삭제할 권한이 없습니다.";
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

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateDeleteAuthority(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        List<DeleteHistory> answersDeleteHistory;

        try {
            answersDeleteHistory = answers.deleteAnswers(loginUser);
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException(DELETE_ANSWERS_AUTHORITY);
        }
        
        this.deleted = true;

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, loginUser, LocalDateTime.now()));
        deleteHistories.addAll(answersDeleteHistory);

        return deleteHistories;
    }

    private void validateDeleteAuthority(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(DELETE_QUESTION_AUTHORITY);
        }
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
        answers.addAnswer(answer);
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
