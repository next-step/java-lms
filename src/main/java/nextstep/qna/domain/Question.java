package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private QuestionContent questionContent;

    private Answers answers;

    private boolean deleted = false;

    private BaseTime baseTime;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.questionContent = new QuestionContent(writer, title, contents);
        this.answers = new Answers(new ArrayList<>());
        this.baseTime = new BaseTime();
    }

    public Long getId() {
        return id;
    }


    public NsUser getWriter() {
        return this.questionContent.writer();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return this.questionContent.equalsWriter(loginUser);
    }


    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> deleteQuestion(NsUser loginUser) throws CannotDeleteException {
        validateQuestionAndAnswers(loginUser);
        delete(true);
        answers.deleteAll();
        return createDeleteHistories();
    }

    private void validateQuestionAndAnswers(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (answers.isNotOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private void delete(boolean deleted) {
        this.deleted = deleted;
    }

    private List<DeleteHistory> createDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.ofQuestion(id, this.questionContent.writer(), LocalDateTime.now()));
        deleteHistories.addAll(answers.createDeleteHistory());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionContent=" + questionContent +
                ", answers=" + answers +
                ", deleted=" + deleted +
                ", baseTime=" + baseTime +
                '}';
    }
}
