package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends BaseTimeEntity {

    private QuestionBody questionBody;

    private Deleted deleted;

    private Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, title, contents, writer, new Deleted());
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, title, contents, writer, new Deleted());
    }

    public Question(Long id, String title, String contents, NsUser writer, Deleted deleted) {
        this(new QuestionBody(id, title, contents, writer), deleted);
    }

    public Question(QuestionBody questionBody, Deleted deleted) {
        this.questionBody = questionBody;
        this.deleted = deleted;
    }

    public Long getId() {
        return this.questionBody.getId();
    }

    public String getTitle() {
        return this.questionBody.getTitle();
    }

    public Question setTitle(String title) {
        this.questionBody.setTitle(title);
        return this;
    }

    public String getContents() {
        return this.questionBody.getContents();
    }

    public Question setContents(String contents) {
        this.questionBody.setContents(contents);
        return this;
    }

    public NsUser getWriter() {
        return this.questionBody.getWriter();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        questionBody.addAnswer(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (isOwner(loginUser)) {
            questionBody.canDeleteAnswers(loginUser);
            return toDeleteHistories();
        }

        throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }

    private boolean isOwner(NsUser loginUser) {
        return this.questionBody.isOwner(loginUser);
    }

    private List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(toDeleteHistory());
        deleteHistories.addAll(questionBody.toDeleteHistory());

        return deleteHistories;
    }

    private DeleteHistory toDeleteHistory() {
        delete();
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
    }

    public void delete() {
        this.deleted.delete();
    }

    public boolean isDeleted() {
        return this.deleted.isDeleted();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + getTitle() + ", contents=" + getContents() + ", writer=" + getWriter() + "]";
    }
}
