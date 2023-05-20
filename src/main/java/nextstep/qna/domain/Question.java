package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question extends BaseEntity {

    private final long id;
    private final NsUser writer;
    private final Answers answers;

    private String title;
    private String contents;

    private boolean deleted;

    public Question(long id, NsUser writer, String title, String contents, Answers answers) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.answers = answers;
    }

    public Question changeTitle(String title) {
        this.title = title;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
    }

    public Question changeContents(String contents) {
        this.contents = contents;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public Question display() {
        this.deleted = false;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
    }

    public Question hide() {
        this.deleted = false;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
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
}
