package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers;

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
        this.answers = Answers.init();
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers = answers.added(answer);
    }

    public List<DeleteHistory> deleted() {
        delete();
        return deletedHistory();
    }

    private void delete() {
        this.deleted = true;
    }

    private List<DeleteHistory> deletedHistory() {
        List<DeleteHistory> result = new ArrayList<>();
        result.add(deletedQuestion());
        result.addAll(deletedAnswer());

        return result;
    }

    private DeleteHistory deletedQuestion() {
        return DeleteHistory.question(this.id, this.writer);
    }

    private List<DeleteHistory> deletedAnswer() {
        return this.answers.deleted();
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean hasAnswerOfOthers() {
        return this.answers.hasAnswerExcept(this.writer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
