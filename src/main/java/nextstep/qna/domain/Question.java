package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.vo.QuestionBody;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private QuestionBody questionBody;

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
        this.questionBody = new QuestionBody(title, contents);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser))
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        this.deleted = true;
        this.updatedDate = LocalDateTime.now();

        List<DeleteHistory> deleteHistoryList = new ArrayList<>();
        DeleteHistory questionDeleteHistory = new DeleteHistory(ContentType.QUESTION, getId(), loginUser, LocalDateTime.now());
        List<DeleteHistory> answersDeleteHistoryList = answers.delete(loginUser);

        deleteHistoryList.add(questionDeleteHistory);
        deleteHistoryList.addAll(answersDeleteHistoryList);

        return deleteHistoryList;
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

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionBody=" + questionBody +
                ", writer=" + writer +
                ", answers=" + answers +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
