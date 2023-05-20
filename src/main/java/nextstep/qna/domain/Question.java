package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private final Answers answers = new Answers(this);
    private final LocalDateTime createdDate = LocalDateTime.now();
    private final Long id;
    private final String title;
    private final String contents;
    private final NsUser writer;
    private boolean deleted = false;
    private LocalDateTime updatedDate;

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> histories = new ArrayList<>();

        histories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
        histories.addAll(answers.deleteAll());

        deleted = true;
        return histories;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
}
