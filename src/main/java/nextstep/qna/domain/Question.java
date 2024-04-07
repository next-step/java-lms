package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<DeleteHistory> delete(NsUser requestUser, LocalDateTime requestDatetime) throws CannotDeleteException {
        List<DeleteHistory> histories = new ArrayList<>();

        DeleteHistory questionDeleteHistory = deleteQuestion(requestUser, requestDatetime);
        histories.add(questionDeleteHistory);
        histories.addAll(answers.deleteAll(requestUser, requestDatetime));

        return histories;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer, this);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private DeleteHistory deleteQuestion(NsUser requestUser, LocalDateTime requestDatetime) throws CannotDeleteException{
        if (!isOwner(requestUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        this.deleted = true;

        return new DeleteHistory(ContentType.QUESTION, this.getId(), this.getWriter(), requestDatetime);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
