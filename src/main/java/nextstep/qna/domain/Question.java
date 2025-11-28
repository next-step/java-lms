package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends SoftDeletableModel {
    private Long id;

    private QuestionBody questionBody;

    private NsUser writer;

    private Answers answers = new Answers();

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, new QuestionBody(title, contents));
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, writer, new QuestionBody(title, contents));
    }

    public Question(Long id, NsUser writer, QuestionBody questionBody) {
        this.id = id;
        this.writer = writer;
        this.questionBody = questionBody;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return getDeleted();
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        deleteQuestion(loginUser);
        answers.deleteAnswer(loginUser);
    }

    private void deleteQuestion(NsUser loginUser) throws CannotDeleteException {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        updateDeleted();
    }

    public List<DeleteHistory> toDeleteHistories(){
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));

        List<DeleteHistory> answerDeleteHistories = addDeleteAnswerHistory();
        deleteHistories.addAll(answerDeleteHistories);

        return deleteHistories;
    }

    private List<DeleteHistory> addDeleteAnswerHistory() {
        return this.answers.addDeleteAnswerHistory();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + questionBody.getTitle() + ", contents=" + questionBody.getContents() + ", writer=" + writer + "]";
    }

}
