package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private QuestionDetails details;

    private final Answers answers = new Answers();

    private boolean deleted = false;

    private ContentsDateTime contentsDateTime = new ContentsDateTime();

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        details = new QuestionDetails(id, writer, title, contents);
    }

    public Long getId() {
        return details.getId();
    }

    public String getTitle() {
        return details.getTitle();
    }


    public Question setTitle(String title) {
        details.setTitle(title);
        return this;
    }
    public String getContents() {
        return details.getContents();
    }

    public Question setContents(String contents) {
         details.setContents(contents);
         return this;
    }

    public NsUser getWriter() {
        return details.getWriter();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return details.getWriter().equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, details.getId(), details.getWriter(), contentsDateTime.getCreatedDate()));
        answers.delete(loginUser, deleteHistories);
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + details.getTitle() + ", contents=" + details.getContents() + ", writer=" + details.getWriter() + "]";
    }
}
