package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseEntity {
    private final long id;

    private final NsUser writer;

    private final Question question;

    private String contents;

    private boolean deleted = false;

    public Answer(long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    public Answer changeContents(String contents) {
        this.contents = contents;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
    }

    public Answer display() {
        this.deleted = false;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
    }

    public Answer hide() {
        this.deleted = false;
        super.modifyUpdateDate(LocalDateTime.now());
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
