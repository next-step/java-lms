package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
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


    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    private void hasDeleteAuthentication(NsUser loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }



    public DeleteHistorys deleteQuestionAndAnswer(NsUser user) throws CannotDeleteException {
        DeleteHistorys historys = new DeleteHistorys();
        historys.add(deleteQuestion(user));
        historys.addAll(deleteAnswer(user).getDeleteHistoryList());
        return historys;
    }

    public DeleteHistory deleteQuestion(NsUser user) throws CannotDeleteException {
        hasDeleteAuthentication(user);
        deleted = true;
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }



    public DeleteHistorys deleteAnswer(NsUser user) throws CannotDeleteException {
        return answers.delete(user);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }


    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
