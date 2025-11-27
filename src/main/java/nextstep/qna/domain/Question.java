package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends SoftDeletableModel {
    private Long id;

    private QuestionBody title;

    private QuestionBody contents;

    private NsUser writer;

    private Answers answers = new Answers();

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, writer, new QuestionBody(title), new QuestionBody(contents));
    }

    public Question(Long id, NsUser writer, QuestionBody title, QuestionBody contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
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

    private void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        updateDeleted();
    }

    public void delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        answers.validateAnswerOwner(loginUser);
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

    private void updateDeleted() {
        deleted();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
