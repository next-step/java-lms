package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends Post{

    private String title;

    private Answers answers = new Answers();

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
        answers.addAnswer(answer);
    }

    public List<DeleteHistory> makeDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(makeDeleteHistory());
        for (Answer answer : answers) {
            deleteHistories.add(answer.makeDeleteHistory());
        }

        return deleteHistories;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public void delete(NsUser loginUser) {
        authorityValidation(loginUser);
        answers.delete(loginUser);
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    private void authorityValidation(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    @Override
    public DeleteHistory makeDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }
}
