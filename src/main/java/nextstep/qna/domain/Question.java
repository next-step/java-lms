package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private Long id;
    private String title;
    private String contents;
    private NsUser writer;
    private List<Answer> answers = new ArrayList<>();
    private Answers answers1 = new Answers();
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
        answers1.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + this.id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> deleteBy(NsUser user) throws CannotDeleteException {
        if (!user.matchUser(this.writer)) {
            throw new CannotDeleteException("현재 로그인 계정과 질문자가 다릅니다.");
        }
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.addAll(this.answers1.deleteBy(user));
        this.deleted = true;
        deleteHistories.add(toHistory());
        return deleteHistories;
    }

    private DeleteHistory toHistory() throws CannotDeleteException {
        if (!isDeleted()) {
            throw new CannotDeleteException("삭제되지 않은 질문입니다.");
        }
        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }
}
