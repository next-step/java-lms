package nextstep.qna.domain;

import nextstep.qna.domain.validate.QuestionRemoveValidator;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<DeleteHistory> remove(NsUser requestUser) {
        QuestionRemoveValidator.validate(this, requestUser);

        this.deleted = false;
        super.modifyUpdateDate(LocalDateTime.now());
        return null;
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
